import org.apache.commons.math3.analysis.function.Max;

public class Student {
    private int studentID;
    private String major;
    private Double testScore;
    private Double retakeScore = null;
    private Double finalScore;
    private char gender;

    public Student(){

    }

    public String toString(){
        String s = "ID: " + studentID + " Major: " + major + " Gender: " + gender + " First Test: " + testScore + " Retake: "
                + retakeScore + " Final Score: " + finalScore;
        return s;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getTestScore() {
        return testScore;
    }

    public void setTestScore(double testScore) {
        this.testScore = testScore;
    }

    public double getRetakeScore() {
        return retakeScore;
    }

    public void setRetakeScore(double retakeScore) {
        this.retakeScore = retakeScore;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void calcFinalScore(){
        if(retakeScore == null){
            finalScore = testScore;
        }else{
            finalScore = Math.max(testScore,retakeScore);
        }
    }
}
