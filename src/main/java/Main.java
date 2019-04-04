import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static final String studentInfoFile = "Student Info.xlsx";
    public static final String testRetakeFile = "Test Retake Scores.xlsx";
    public static final String testScores = "Test Scores.xlsx";
    public static ArrayList<Student> students = new ArrayList<Student>();
    public static double averageScore;

    public static void createStudents()throws  IOException{
        Workbook workbook = WorkbookFactory.create(new File(studentInfoFile));//open excel file
        Sheet sheet = workbook.getSheetAt(0);
        for(Row row: sheet){//get values from each row
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
    }

    public static void setGrades(String file,boolean retake)throws IOException{
        Workbook workbook = WorkbookFactory.create(new File(file));//open excel file
        Sheet sheet = workbook.getSheetAt(0);

        for(Row row: sheet) {//get values from each row
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


    public static void main(String[] args)throws IOException{
        ArrayList<Integer> femaleCompSciList = new ArrayList<Integer>();
        createStudents();
        setGrades(testScores,false);
        setGrades(testRetakeFile,true);

        for(Student s :students){
            s.calcFinalScore();
            if(s.getGender() == 'F' && s.getMajor().equalsIgnoreCase("computer science")){
                femaleCompSciList.add(s.getStudentID());
            }
            System.out.println(s.toString());
            averageScore += s.getFinalScore();
        }
        System.out.println(femaleCompSciList.toString());
        Collections.sort(femaleCompSciList);
        System.out.println(femaleCompSciList.toString());

        averageScore = averageScore/students.size();
        int finalAverageScore = (int) Math.round(averageScore);
        System.out.println(averageScore);
        System.out.println(finalAverageScore);
    }
}
