package pro1;

import pro1.apiDataModel.ActionsList;
import pro1.apiDataModel.Action;
import pro1.apiDataModel.Teacher;
import pro1.apiDataModel.TeachersList;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Main3 {

    public static void main(String[] args) {
        System.out.println(emailOfBestTeacher("KIKM",2024));
    }

    public static String emailOfBestTeacher(String department, int year)
    {  //  - Stáhni seznam učitelů na katedře
        String teachersJson = Api.getTeachersByDepartment(department);
        Gson gson = new Gson();
        TeachersList teachersList = gson.fromJson(teachersJson, TeachersList.class);

        //  - Stáhni seznam akcí na katedře
        String actionsJson = Api.getActionsByDepartment(department, year);
        ActionsList actionsList = gson.fromJson(actionsJson, ActionsList.class); //parsování json řetězce do třídy

        if(teachersList != null && teachersList.items != null && actionsList != null && actionsList.items != null) {
            Map<Long, Long> teacherScores = new HashMap<>(); //nová mapa pro uložení skóre učitelů
            for(Teacher teacher : teachersList.items) { //projde učitele
                long score = TeacherScore(teacher.id, actionsList); //volání metody pro výpočet
                teacherScores.put(teacher.id, score); //uložení id a skóre do mapy teacherScores
            }
            long bestTeacherId = -1;
            long maxScore = -1;
            //  - Najdi učitele s nejvyšším "score" a vrať jeho e-mail
            for(Map.Entry<Long, Long> entry : teacherScores.entrySet()) {
                if(entry.getValue() > maxScore) {
                    maxScore = entry.getValue();
                    bestTeacherId = entry.getKey();
                }
            }
            if(bestTeacherId != -1) {
                for(Teacher teacher : teachersList.items) {
                    if(teacher.id == bestTeacherId) {
                        return teacher.email;
                    }
                }
            }
        }


        return "";
    }

    public static long TeacherScore(long teacherId, ActionsList departmentSchedule)
    { long totalScore = 0;
        if(departmentSchedule != null && departmentSchedule.items != null) { //kotrola akcí a jestli nejsou prázdné
            for(Action action : departmentSchedule.items){
                if (action.teacherId == teacherId) { //kontrola, zda id učitele u akce odpovídá hledanému učiteli
                    totalScore += action.personsCount;
                }
            }
        }
        return totalScore;
    }
}