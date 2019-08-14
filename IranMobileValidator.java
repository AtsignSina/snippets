import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IranMobileValidator {
  private static List<String> prefixes = new ArrayList<>();

  static {
    initializePrefixes();
  }

  private static void initializePrefixes() {
    String[] pfx = {
      // IR-MCI
      "910",
      "911",
      "912",
      "913",
      "914",
      "915",
      "916",
      "917",
      "918",
      "919",
      "990",
      "991",
      "992",
      // Irancell
      "930",
      "933",
      "935",
      "936",
      "937",
      "938",
      "939",
      "901",
      "902",
      "903",
      "904",
      "905",
      // Rightell
      "920",
      "921",
      "922",
      // ApTel
      "99910",
      "99911",
      "99913",
      // AzarTel
      "99914",
      // SamanTel
      "99996",
      "99997",
      "99998",
      "99999",
      // LotusTel
      "9990",
      // ShatelMobile
      "99810",
      "99811",
      "099812",
      // Anarestan
      "9944"
    };
    prefixes.addAll(Arrays.asList(pfx));
  }

  public static String normalize(String mobile) {
    if (mobile == null || mobile.isEmpty()) {
      throw new IllegalArgumentException("INPUT_IS_INVALID");
    }
    Matcher matcher = Pattern.compile("^(\\+|00)?(98(0)?|0)?(9\\d{9})$").matcher(mobile.trim());
    if (matcher.matches()) {
      return "+98" + matcher.group(4);
    } else throw new IllegalArgumentException("INPUT_IS_INVALID");
  }

  public static boolean check(String input) {
    return input != null
        && !input.isEmpty()
        && input.matches("^\\+989\\d{9}$")
        && prefixes.stream().anyMatch(prf -> prf.equals(input.substring(3, prf.length() + 3)));
  }
}
