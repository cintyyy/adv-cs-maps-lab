public class State{
    private String abbreviation;
    public State(String abbreviation){
        this.abbreviation = abbreviation;
    }
    @Override
    public int hashCode(){
        String abb = abbreviation.toLowerCase();
        int first = abb.charAt(0) - 'a';
        int second = abb.charAt(1) - 'a';
        return first * 26 + second;
    }
    public String getAbb(){
        return abbreviation;
    }

    @Override
    public boolean equals(Object o) {
        //Return true if the first letter of the name and age are the same
        State s = (State) o;
        if (s.getAbb().charAt(0) == abbreviation.charAt(0) && s.getAbb().charAt(1) == abbreviation.charAt(1)) {
            return true;
        }
        return false;
    }
}