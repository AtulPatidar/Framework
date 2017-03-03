package com.xpanxion.xpert.generators;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringRegex {

    public String stringFromRegex(String regex) {
        String regexConverted = cvtLineTerminators(regex);

        String fullString = "";
        fullString = parse(regexConverted, "");
        return fullString;
    }

    private String parse(String regex, String fullString) {
        Random random = new Random();
        if (regex.trim().length() > 0) {
            Boolean allow = false, processed = false;
            if (regex.startsWith("[")
                    && (regex.substring(0, regex.indexOf("]") + 1).matches("(.*)[a-z]\\-[a-z](.*)")
                    || regex.substring(0, regex.indexOf("]") + 1).matches("(.*)[A-Z]\\-[A-Z](.*)")
                    || regex.substring(0, regex.indexOf("]") + 1).matches("(.*)d(.*)")
                    || regex.substring(0, regex.indexOf("]") + 1).matches("(.*)D(.*)")
                    || regex.substring(0, regex.indexOf("]") + 1).matches("(.*)w(.*)")
                    || regex.substring(0, regex.indexOf("]") + 1).matches("(.*)W(.*)")
                    || regex.substring(0, regex.indexOf("]") + 1).matches("\\[([a-z]*)([0-9]*).\\]")
                    || regex.substring(0, regex.indexOf("]") + 1).matches("\\[[A-Z].\\]")
                    || regex.substring(0, regex.indexOf("]") + 1).matches("(.*)[0-9]\\-[0-9](.*)"))) {
                allow = true;
            }
            if (allow) {
                int start = regex.indexOf("[");
                int end = regex.indexOf("]", start);
                String part = regex.substring(start, end + 1);
                regex = regex.substring(end + 1);
                int pos1 = 1, pos2 = 1;
                if (regex.startsWith("{")) {
                    start = 0;
                    String pos = "";
                    end = regex.indexOf("}", start);
                    pos = regex.substring(start + 1, end);
                    //part = part + regex.substring(start, end + 1);
                    regex = regex.substring(end + 1);

                    if (pos.trim().length() > 0) {
                        if (pos.contains(",")) {
                            String[] poss = pos.split(",");
                            pos1 = Integer.parseInt(poss[0]);
                            pos2 = Integer.parseInt(poss[1]);
                        } else {
                            pos1 = Integer.parseInt(pos);
                            pos2 = Integer.parseInt(pos);
                        }
                    }
                }
                //

                StringBuilder sb = new StringBuilder();
                String str = "";
                String printInfo = "Processing-1: " + part;
                int tried = 0;
                while (sb.toString().length() == 0 && tried <= 5) {
                    tried++;
                    str = RandomString.nextString(2000);
                    try {
                        Matcher m = Pattern.compile(part).matcher(str);
                        while (m.find()) {
                            sb.append(m.group(0).toString());
                        }
                    } catch (Exception ex) {

                    }
                }
                if (sb.toString().length() > 0) {
                    processed = true;
                    str = sb.toString();
                    while (true) {
                        if (str.length() < pos2) {
                            str = str.concat(str);
                        } else {
                            break;
                        }
                    }
                    printInfo += " = (" + pos1 + ", " + pos2 + "): ";
                    String cutString = str.substring(0, pos1);
                    if (pos2 - pos1 > 0) {
                        pos2 = random.nextInt(pos2 - pos1);
                        cutString += str.substring(pos1, pos2 + pos1);
                    }
                    printInfo += cutString;
                    //
                    fullString += cutString;
                } else {
                    regex = part + regex;
                }
            }
            if (regex.startsWith("[") && !processed) {
                processed = true;
                int start = regex.indexOf("[");
                int end = regex.indexOf("]", start);
                String part = regex.substring(start + 1, end);
                regex = regex.substring(end + 1);
                String printInfo = "Processing-2: " + part;
                int pos1 = 1, pos2 = 1;
                if (regex.startsWith("{")) {
                    start = 0;
                    String pos = "";
                    end = regex.indexOf("}", start);
                    pos = regex.substring(start + 1, end);
                    regex = regex.substring(end + 1);

                    if (pos.trim().length() > 0) {
                        if (pos.contains(",")) {
                            String[] poss = pos.split(",");
                            pos1 = Integer.parseInt(poss[0]);
                            pos2 = Integer.parseInt(poss[1]);
                        } else {
                            pos1 = Integer.parseInt(pos);
                            pos2 = Integer.parseInt(pos);
                        }
                    }
                }
                String pushString = "";
                for (int i = 0; i < pos1; i++) {
                    pushString += part;
                }
                if (pos2 - pos1 > 0) {
                    pos2 = random.nextInt(pos2 - pos1);
                    for (int i = 0; i < pos2; i++) {
                        pushString += part;
                    }
                }
                printInfo += "{" + pos1 + "," + pos2 + "} = " + pushString;
                //
                fullString += pushString;
            }
            if (!processed) {
                //
                regex = "[" + regex;
                int fa = regex.indexOf("]"), f2 = regex.indexOf("{"), f3 = regex.indexOf("[", 1);
                if (fa > f3) {
                    fa = f3;
                }
                if (fa > f2) {
                    fa = f2;
                }
                if (fa < 0) {
                    if (f2 < f3 && f2 >= 0) {
                        fa = f2;
                    } else if (f3 > f2 && f3 >= 0) {
                        fa = f3;
                    } else {
                        fa = regex.length();
                    }
                }
                //regex = regex.substring(0, fa) + "]" + regex.substring(fa);
                regex = regex.substring(0, 2) + "]" + regex.substring(2);
            }
            fullString = parse(regex, fullString);
        }
        return fullString;
    }

    private String cvtLineTerminators(String s) {
        s = s.replaceAll("\\*", "{0,10}");
        s = s.replaceAll("\\+", "{1,10}");

        StringBuffer sb = new StringBuffer();
        int oldindex = 0, newindex;
        while ((newindex = s.indexOf("\\n", oldindex)) != -1) {
            sb.append(s.substring(oldindex, newindex));
            oldindex = newindex + 2;
            sb.append('\n');
        }
        sb.append(s.substring(oldindex));

        s = sb.toString();
        sb = new StringBuffer();
        oldindex = 0;
        while ((newindex = s.indexOf("\\r", oldindex)) != -1) {
            sb.append(s.substring(oldindex, newindex));
            oldindex = newindex + 2;
            sb.append('\r');
        }
        sb.append(s.substring(oldindex));

        s = sb.toString();
        sb = new StringBuffer();
        oldindex = 0;
        while ((newindex = s.indexOf("\\s", oldindex)) != -1) {
            sb.append(s.substring(oldindex, newindex));
            oldindex = newindex + 2;
            sb.append(" ");
        }
        sb.append(s.substring(oldindex));

        return sb.toString();
    }
}
