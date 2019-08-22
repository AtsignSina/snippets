
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class BaseEnumList {
    protected BaseEnumList() {
    }

    public static final int ___UNDEFINED = Integer.MIN_VALUE;

    public SimpleEnum[] all() {
        return listAllFields().toArray(new SimpleEnum[0]);
    }

    private Set<SimpleEnum> listAllFields() {
        Field[] fields = this.getClass().getFields();
        Set<SimpleEnum> set = new TreeSet<>(SimpleEnum::compareTo);
        for (Field field : fields) {
            if (field.getType() == int.class && field.getModifiers() == Modifier.PUBLIC + Modifier.STATIC + Modifier.FINAL) {
                fieldToSimpleEnum(field).ifPresent(set::add);
            }
        }
        return set;
    }

    private Optional<SimpleEnum> fieldToSimpleEnum(Field field) {
        Integer value;
        try {
            value = field.getInt(null);
        } catch (Exception e) {
            value = null;
        }
        return value != null ? Optional.of(new SimpleEnum(field.getName(), value)) : Optional.empty();
    }

    public Integer[] values() {
        return listAllFields().stream().map(SimpleEnum::getValue).toArray(Integer[]::new);
    }

    public String[] names() {
        return listAllFields().stream().map(SimpleEnum::getName).toArray(String[]::new);
    }

    public String name(Integer value) {
        Optional<SimpleEnum> opt = this.nameOfIfExist(value);
        return opt.map(SimpleEnum::getName).orElse(null);
    }

    public Optional<SimpleEnum> nameOfIfExist(Integer name) {
        return Optional.of(nameOf(name));
    }

    public SimpleEnum nameOf(int value) {
        List<SimpleEnum> list = listAllFields().stream().filter(se -> {
            return se.getValue() != null && se.getValue() == value;
        }).collect(Collectors.toList());
        return list.isEmpty() ? null : list.get(0);
    }

    public Integer value(String name) {
        Optional<SimpleEnum> opt = this.valueOfIfExist(name);
        return opt.isPresent() ? opt.get().getValue() : ___UNDEFINED;
    }

    public Optional<SimpleEnum> valueOfIfExist(String name) {
        return Optional.of(valueOf(name));
    }

    public SimpleEnum valueOf(String name) {
        List<SimpleEnum> list = listAllFields().stream().filter(se -> {
            return se.getName().equals(name);
        }).collect(Collectors.toList());
        return list.isEmpty() ? null : list.get(0);
    }

    public boolean has(String name) {
        List<SimpleEnum> list = listAllFields().stream().filter(se -> {
            return se.getName().equals(name);
        }).collect(Collectors.toList());
        return list.size() > 0;
    }

    public boolean has(Integer name) {
        List<SimpleEnum> list = listAllFields().stream().filter(se -> {
            return se.getValue().equals(name);
        }).collect(Collectors.toList());
        return list.size() > 0;
    }
}
