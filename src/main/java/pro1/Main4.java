package pro1;

import com.google.gson.Gson; 
import pro1.apiDataModel.Teacher;
import pro1.apiDataModel.TeachersList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main4 {

    public static void main(String[] args) {
         printShortestEmails("KIKM",5);
    }

    public static void printShortestEmails(String department, int count)
    {
        // TODO 4.1: Vypiš do konzole "count" nejkratších učitelských emailových adres
        String teachersJson = Api.getTeachersByDepartment(department);
        Gson gson = new Gson();
        TeachersList teachersList = gson.fromJson(teachersJson, TeachersList.class);
        
        if(teachersList != null && teachersList.items != null){
            List<Teacher> sortedTeachers = teachersList.items.stream()
                    .sorted(Comparator.comparingInt(t -> t.email.length())) //seřazení podle délky emailu
                    .limit(count)
                    .collect(Collectors.toList());
            for (Teacher teacher : sortedTeachers) {
                System.out.println(teacher.email);
            }
        }
    }
}