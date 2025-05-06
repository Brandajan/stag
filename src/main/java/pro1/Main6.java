package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;
import pro1.apiDataModel.Action;
import java.util.Map; // práce s mapami
import java.util.HashMap; // konkrétní implementace mapy
import java.util.Comparator; //porovnávání
import java.util.Optional; // práce s optional

public class Main6 {

    public static void main(String[] args) {
        System.out.println(idOfBestTeacher("KIKM",2024));
    }

    public static long idOfBestTeacher(String department, int year)
    {
        // TODO 6.1 (navazuje na TODO 3):
        //  - Stáhni seznam akcí na katedře (jiná data nepoužívat)
        //  - Najdi učitele s nejvyšším "score" a vrať jeho ID
        String actionsJson = Api.getActionsByDepartment(department, year);
        Gson gson = new Gson();
        ActionsList actionsList = gson.fromJson(actionsJson, ActionsList.class);

        if (actionsList != null && actionsList.items != null) {
            Map<Long, Long> teacherScores = new HashMap<>();

            for (Action action : actionsList.items) {
                long teacherId = action.teacherId; // id učitele z aktuální akce
                int personsCount = action.personsCount;
                teacherScores.put(teacherId, teacherScores.getOrDefault(teacherId, 0L) + personsCount);
            }

            Optional<Map.Entry<Long, Long>> bestTeacherEntry = teacherScores.entrySet().stream() // vytvoření streamu z položek mapy se skóre učitelů
                    .max(Map.Entry.comparingByValue()); // nalezení položky s nejvyšším skóre

            if (bestTeacherEntry.isPresent()) {
                return bestTeacherEntry.get().getKey(); // vrácení ID učitele s nejvyšším skóre
            }
        }
        return 0;
    }
}