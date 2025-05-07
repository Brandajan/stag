package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.Specialization;
import pro1.apiDataModel.SpecializationList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main7 {

    public static String specializationDeadines(int year){
        String json = Api.getSpecializations(year); //data přijímacích řízení
        Gson gson = new Gson();
        SpecializationList specializationList = gson.fromJson(json, SpecializationList.class);
        if (specializationList != null && specializationList.items != null) {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_LOCAL_DATE; //formátovač pro formát dat
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            List<LocalDate> deadLines = new ArrayList<>();
            for (Specialization specialization : specializationList.items) {
                if(specialization.deadlinePrihlaska != null && specialization.deadlinePrihlaska.value != null){
                    try{
                        LocalDate date = LocalDate.parse(specialization.deadlinePrihlaska.value, DateTimeFormatter.ISO_LOCAL_DATE); //parsovaní hodnoty uzávěrky do LocalDate
                        deadLines.add(date);
                    } catch (Exception e) {
                        System.err.println("Nepodařilo se naparsovat: " + specialization.deadlinePrihlaska.value);
                    }
                }
            }
            return deadLines.stream()
                    .sorted() //seřazení dle čas. osy
                    .map(outputFormatter::format) //mapování LocalDate objektů
                    .distinct() //odstranění duplicitních datumů
                    .collect(Collectors.joining(",")); //sběr zformátovaných řetězců
        }else {
            return "";
        }
    }
}
