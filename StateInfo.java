public class StateInfo {
    private String capitalCity;
    private int population;
    private DLList<String> images;
    public StateInfo (String capitalCity, int population){
        this.capitalCity = capitalCity;
        this.population = population;
        this.images = new DLList<String>();
    }

    public String toString(){
        return "Capital City: " + capitalCity + " | Population: " + population + " million";
    }
}