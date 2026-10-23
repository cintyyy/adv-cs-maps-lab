public class StateInfo {
    private String name;
    private String capitalCity;
    private int population;
    public Info (String name, String capitalCity, int population){
        this.name = name;
        this.capitalCity = capitalCity;
        this.population = population;
    }

    public String toString(){
        return "Name: " + name + " | Capital City: " + capitalCity + " | Population: " + population;
    }
}