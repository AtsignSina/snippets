
public class SimpleEnum {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    private Integer value;

    public SimpleEnum() {
    }

    public SimpleEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static int compareTo(SimpleEnum o1, SimpleEnum o2) {
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 != null && o2 != null) {
            return Integer.compare(o1.getValue(), o2.getValue());
        } else return 1;
    }
}
