public class StateInfo {
    private String capitalCity;
    private int population;
    private DLList<String> images;
    public StateInfo (String capitalCity, int population){
        this.capitalCity = capitalCity;
        this.population = population;
        this.images = new DLList<String>();
    }

    public DLList<String> getImages(){
        return images;
    }
    public void addImage(String url){
        images.add(url);
    }
    public int getPopulation(){
        return population;
    }
    public String getCapitalCity(){
        return capitalCity;
    }
    public String toString(){
        return "Capital City: " + capitalCity + " | Population: " + population + " million";
    }
}