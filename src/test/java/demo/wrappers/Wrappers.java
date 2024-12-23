package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.time.Duration;

public class Wrappers {
    public static Boolean printTitleAndImageOFTopFiveMugs(WebDriver driver, By locator){
        Boolean success;
        try{
            List <WebElement> UserReviewsEle = driver.findElements(locator);
            Set <Integer> userReviewSet = new HashSet<>();
            for(WebElement userReview : UserReviewsEle){
                int userReviewInt = Integer.parseInt(userReview.getText().replaceAll("[^\\d]",""));
                userReviewSet.add(userReviewInt);
            }
            List<Integer> userReviewCountList = new ArrayList<>(userReviewSet);
            Collections.sort(userReviewCountList,Collections.reverseOrder());
            System.out.println(userReviewCountList);
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            LinkedHashMap<String, String> ProductDetailsMap = new LinkedHashMap<>();
            for(int i = 0; i<5; i++){
                Thread.sleep(2000);
                String FormattedUserReviewCount = "("+numberFormat.format(userReviewCountList.get(i))+")";
                String ProductTitle = driver.findElement(By.xpath("//div[@class='slAVV4']//span[contains(text(),'"+FormattedUserReviewCount+"')]/../../a[@class='wjcEIp']")).getText();
                String ProductImageURL = driver.findElement(By.xpath("//div[@class='slAVV4']//span[contains(text(),'"+ FormattedUserReviewCount+"')]/../..//img[@class='DByuf4']")).getAttribute("src");
                String HighestReviewsCountAndProductTitle = String.valueOf(i+1)+"Highest review count: " +FormattedUserReviewCount + "title" + ProductTitle;
                ProductDetailsMap.put(HighestReviewsCountAndProductTitle, ProductImageURL); 
            }
            for(Map.Entry<String,String> productDetail : ProductDetailsMap.entrySet()){
                System.out.println(productDetail.getKey()+"and Product image URL"+ productDetail.getValue());
            }
            success = true;
        }catch(Exception e){
            System.out.println("Exception Occured!"+ e.getMessage());
            e.printStackTrace();
            success = false;
        }
        return success;
        
    }
    /*
     * Write your selenium wrappers here
     */
}
