public class Student {
    private int studentID;
    private String major;
    private Double testScore;
    private Double retakeScore = null;
    private Double finalScore;
    private char gender;

    Student(){

    }


    public String toString(){
        String s = "ID: " + studentID + " Major: " + major + " Gender: " + gender + " First Test: " + testScore + " Retake: "
                + retakeScore + " Final Score: " + finalScore;
        return s;
    }

    int getStudentID() {
        return studentID;
    }

    void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    String getMajor() {
        return major;
    }

    void setMajor(String major) {
        this.major = major;
    }

    double getTestScore() {
        return testScore;
    }

    void setTestScore(double testScore) {
        this.testScore = testScore;
    }

    double getRetakeScore() {
        return retakeScore;
    }

    void setRetakeScore(double retakeScore) {
        this.retakeScore = retakeScore;
    }

    double getFinalScore() {
        return finalScore;
    }

    void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

    char getGender() {
        return gender;
    }

    void setGender(char gender) {
        this.gender = gender;
    }

    void calcFinalScore(){//final = higher between first test and retake
        if(retakeScore == null){
            finalScore = testScore;
        }else{
            finalScore = Math.max(testScore,retakeScore);
        }
    }
}
