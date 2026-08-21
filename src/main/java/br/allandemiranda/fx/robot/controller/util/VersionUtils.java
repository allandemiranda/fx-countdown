package br.allandemiranda.fx.robot.controller.util;

import br.allandemiranda.fx.robot.annotation.field.Version;
import java.util.Arrays;
import java.util.Comparator;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.UnknownNullability;
import org.jspecify.annotations.NullMarked;

@NullMarked
@Slf4j
@UtilityClass
public class VersionUtils {

  private static final String VERSION_FORMAT = "%d.%d.%d";

  private static int @UnknownNullability [] getSplit(@Version String version) {
    return Arrays.stream(version.split("\\."))
        .map(String::trim)
        .mapToInt(Integer::parseInt)
        .toArray();
  }

  @Contract(pure = true)
  public static Comparator<String> comparator() {
    return (v1, v2) -> Arrays.compare(getSplit(v1), getSplit(v2));
  }

  @Contract(pure = true)
  public static int compare(@Version String v1, @Version String v2) {
    return Arrays.compare(getSplit(v1), getSplit(v2));
  }

  @Contract(pure = true)
  public static String getNextMajorVersion(@Version String version) {
    int[] split = getSplit(version);
    return VERSION_FORMAT.formatted(split[0] + 1, split[1], split[2]);
  }

  @Contract(pure = true)
  public static String getNextMinorVersion(@Version String version) {
    int[] split = getSplit(version);
    return VERSION_FORMAT.formatted(split[0], split[1] + 1, split[2]);
  }

  @Contract(pure = true)
  public static String getNextPatchVersion(@Version String version) {
    int[] split = getSplit(version);
    return VERSION_FORMAT.formatted(split[0], split[1], split[2] + 1);
  }

}