import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import java.io.IOException;

public class Main {
    private static final String testRetakeFile = "Test Retake Scores.xlsx";
    private static final String testScores = "Test Scores.xlsx";
    private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    public static void main(String[] args)throws IOException{
        ExcelHandler excelHandler = new ExcelHandler();
        excelHandler.createStudents();//create student objects for student list
        excelHandler.setGrades(testScores,false);//set grades for the students
        excelHandler.setGrades(testRetakeFile,true);

        JSONObject obj = new JSONObject();
        obj.put("id","pnarup@luc.edu");
        obj.put("name","Paul Narup");
        obj.put("average",excelHandler.calcFinalAverage());
        obj.put("studentIds",excelHandler.getFemaleCompArrayStringSorted());
        System.out.println(obj);
        try {
            HttpPost request = new HttpPost("http://3.86.140.38:5000/challenge");
            StringEntity jsonToString = new StringEntity(obj.toString());
            request.addHeader("content-type","application/json");
            request.setEntity(jsonToString);
            HttpResponse response = httpClient.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if(code == 200){
                System.out.println("Code " + code + ": Http post request successful");
            }else {
                System.out.println("Code " + code + ": Http post request not successful");
            }
        }catch (Exception ex){
            System.out.println("Error occurred during post request");
        }finally {
            httpClient.close();
        }
    }
}
