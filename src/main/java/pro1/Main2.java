package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;
import pro1.apiDataModel.Action;

public class Main2 {
    public static void main(String[] args) {
        System.out.println(maxPersonsCount("KIKM",2024));
    }

    public static long maxPersonsCount(String department, int year)
    {
        String json = Api.getActionsByDepartment(department,year);
        ActionsList actions= new Gson().fromJson(json, ActionsList.class);

        if(actions!=null && actions.items != null && !actions.items.isEmpty()){
            int maxCount = 0;
            for (Action action : actions.items){
                if(action.personsCount > maxCount){
                    maxCount = action.personsCount;
                }
            }
            return maxCount;
        } else {
            return 0;
        }
    }
}