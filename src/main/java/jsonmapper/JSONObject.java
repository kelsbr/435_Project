package jsonmapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONObject {

    private String reviewerID, reviewText;
    private final String JSON;

    public JSONObject(String json){
        this.JSON = json;
        this.reviewerID = get("reviewerID");
        this.reviewText = get("reviewText");
    }

    public String get(String field) {
        Pattern pattern = Pattern.compile("\"" + field + "\": ");
        Matcher matcher = pattern.matcher(JSON);
        if(matcher.find()){
            boolean in_quotes = false;
            int i = matcher.end();
            char c;
            while(true){
                c = JSON.charAt(i);
                if(c == ',' && !in_quotes){
                    break;
                }
                if(c == '\"' && JSON.charAt(i-1) != '\\'){
                    in_quotes = !in_quotes;
                }
                i++;
            }

            return JSON.substring(matcher.end(),i).replaceAll("^\"|\"$", "");

        }
        return "";
    }

    public String getReviewerID() {
        return reviewerID;
    }

    public String getReviewText() {
        return reviewText;
    }

    //    public String getFirstKey(){
//        return this.map.firstKey();
//    }

//    public String getFirstValue(){
//        return get(getFirstKey());
//    }

}
