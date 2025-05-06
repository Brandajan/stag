package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;
import pro1.apiDataModel.Action;
import java.util.Set; //práce s množinami
import java.util.TreeSet; //seřazená množina
import java.util.stream.Collectors; //stream API

public class Main5 {

    public static void main(String[] args) {
        System.out.println(roomsSummary("KIKM",2024));
    }

    public static String roomsSummary(String department, int year)
    {// TODO 5.1: Vrať výpis učeben, které katedra v daném roce využila (seřadit abecedně, oddělit čárkou)
        String actionsJSon = Api.getActionsByDepartment(department,year); //Json data
        Gson gson  = new Gson();
        ActionsList actionsList = gson.fromJson(actionsJSon, ActionsList.class);//převedení Json na Actionlist

        if (actionsList != null && actionsList.items != null) {
            Set<String> rooms = new TreeSet<>(); //vytvoření množiny pro názvy učeben
            for (Action action : actionsList.items) {
                if(action.title != null && !action.title.isEmpty()){
                    rooms.add(action.title);
                }
            }
            return String.join(",", rooms); //spojení názvů
        } else {
            return "";
        }

    }
}