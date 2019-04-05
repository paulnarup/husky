import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

class ExcelHandler {
    private final String studentInfoFile = "Student Info.xlsx";
    private ArrayList<Student> students = new ArrayList<Student>();
    private double averageScore;
    private ArrayList<Integer> femaleCompSciList = new ArrayList<Integer>();
    private String[] femaleCompArrayStringSorted;


    ExcelHandler(){}

    void createStudents()throws IOException {//Create student objects based on student info excel file
        Workbook workbook = WorkbookFactory.create(new File(studentInfoFile));
        Sheet sheet = workbook.getSheetAt(0);
        for(Row row: sheet){
            if(row.getRowNum() ==0){
                continue;
            }
            int id = (int) row.getCell(0).getNumericCellValue();
            String major = row.getCell(1).getStringCellValue();
            char gender = row.getCell(2).getStringCellValue().charAt(0);
            Student student = new Student();
            student.setStudentID(id);
            student.setMajor(major);
            student.setGender(gender);
            students.add(student);
        }
        workbook.close();
    }

    void setGrades(String file,boolean retake)throws IOException{ //get grades from excel and apply to student objects
        Workbook workbook = WorkbookFactory.create(new File(file));
        Sheet sheet = workbook.getSheetAt(0);

        for(Row row: sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            int id = (int) row.getCell(0).getNumericCellValue();
            double score = row.getCell(1).getNumericCellValue();
            for(Student student: students){
                if (student.getStudentID() == id){
                    if(retake){
                        student.setRetakeScore(score);
                    }else {
                        student.setTestScore(score);
                    }
                }
            }
        }
        workbook.close();
    }

    int calcFinalAverage(){//calc final class average
        for(Student s : students){
            s.calcFinalScore();
            if(Character.toUpperCase(s.getGender()) == 'F' && s.getMajor().equalsIgnoreCase("computer science")){
                femaleCompSciList.add(s.getStudentID());
            }
            averageScore += s.getFinalScore();
        }
        averageScore = averageScore/students.size();
        int finalAverageScore = (int) Math.round(averageScore);
        return finalAverageScore;
    }

    String[] getFemaleCompArrayStringSorted() {//get females in comp sci sorted by id
        femaleCompArrayStringSorted = new String[femaleCompSciList.size()];
        Collections.sort(femaleCompSciList);
        for (int i = 0; i<femaleCompSciList.size();i++){
            femaleCompArrayStringSorted[i] = String.valueOf(femaleCompSciList.get(i));
        }

        return femaleCompArrayStringSorted;
    }

    String getStudentInfoFile() {
        return studentInfoFile;
    }

    ArrayList<Student> getStudents() {
        return students;
    }

    void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    double getAverageScore() {
        return averageScore;
    }

    void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    ArrayList<Integer> getFemaleCompSciList() {
        return femaleCompSciList;
    }

    void setFemaleCompSciList(ArrayList<Integer> femaleCompSciList) {
        this.femaleCompSciList = femaleCompSciList;
    }

    void setFemaleCompArrayStringSorted(String[] femaleCompArrayStringSorted) {
        this.femaleCompArrayStringSorted = femaleCompArrayStringSorted;
    }
}
