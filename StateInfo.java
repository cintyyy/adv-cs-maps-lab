public class StateInfo {
    private String capitalCity;
    private int population;
    public StateInfo (String capitalCity, int population){
        this.capitalCity = capitalCity;
        this.population = population;
    }

    public String toString(){
        return "Capital City: " + capitalCity + " | Population: " + population + " million";
    }
}