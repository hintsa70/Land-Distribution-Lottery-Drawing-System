package application;

import java.util.*;

public class Imported_mahberat {
//
//    public List<Grade_9And10_excel_mark> marks(String studentId){
//        return Grade_9And10_excel_mark.where("studentId=?", studentId);
//    }
//
//    public List<Imported_student_grade> learnedGrades(String studentId){
//        return Imported_student_grade.where("studentId=?", studentId).orderBy("grade desc");
//    }
//    public List<Imported_student_grade> learnedPreparatoryGrades(String studentId){
//        return Imported_student_grade.where("studentId=?", studentId).orderBy("grade asc");
//    }
//    public Grade_9And10_excel_mark subjectMark(String studentId, String year, String semester){
//
//        return Grade_9And10_excel_mark.findFirst("studentId=? && acadamicYear=? && semester=?", studentId, year, semester);
//    }
//    public Grade_11And12_excel_mark subjectPreparatoryMark(String studentId, String year, String semester){
//
//        return Grade_11And12_excel_mark.findFirst("studentId=? && acadamicYear=? && semester=?", studentId, year, semester);
//    }
//
//    public Grade_11And12_social_excel_mark subjectSocialPreparatoryMark(String studentId, String year, String semester){
//
//        return Grade_11And12_social_excel_mark.findFirst("studentId=? && acadamicYear=? && semester=?", studentId, year, semester);
//    }

    public double roundNumberTo(double n, int precision){
        double factor=Math.pow(10, precision);

       double num= n*factor;
        num=Math.round(num);

        return num/factor;
    }
}
