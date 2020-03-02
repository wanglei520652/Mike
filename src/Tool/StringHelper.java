package Tool;




import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;

/**
* @Package��cn.ucaner.framework.utils   
* @ClassName��StringHelper   
* @Description��   <p>---�ַ���������---</p>
* @Author�� - Jason 
* @CreatTime��2017��8��30�� ����2:06:53   
* @Modify By��   
* @ModifyTime��  
* @Modify marker��   
* @version    V1.0
 */
public class StringHelper {

//    public static Log logger = LogFactory.getLog(StringHelper.class);

    /**
     * ǰ����ʶ
     */
    public static final int BEFORE = 1;

    /**
     * ��̱�ʶ
     */
    public static final int AFTER = 2;

    public static final String DEFAULT_PATH_SEPARATOR = ",";

    /**
     * ��һ���м�����ŷָ������ַ���ת��ΪArrayList����
     * 
     * @param str
     *            ��ת���ķ�������
     * @return ArrayList����
     */
    public static ArrayList<?> strToArrayList(String str) {
        return strToArrayListManager(str, DEFAULT_PATH_SEPARATOR);
    }

    /**
     * ���ַ������󰴸����ķָ���separatorת��ΪArrayList����
     * 
     * @param str
     *            ��ת�����ַ�������
     * @param separator
     *            �ַ��ͷָ���
     * @return ArrayList����
     */
    public static ArrayList<String> strToArrayList(String str, String separator) {
        return strToArrayListManager(str, separator);
    }

    /**
     * @Description: �ָ�
     * @param str
     * @param separator
     * @return ArrayList<String>
     * @Autor: jasonandy@hotmail.com
     */
    private static ArrayList<String> strToArrayListManager(String str, String separator) {

        StringTokenizer strTokens = new StringTokenizer(str, separator);
        ArrayList<String> list = new ArrayList<String>();

        while (strTokens.hasMoreTokens()) {
            list.add(strTokens.nextToken().trim());
        }

        return list;
    }

    /**
     * ��һ���м�����ŷָ������ַ���ת��Ϊ�ַ����������
     * 
     * @param str
     *            ��ת���ķ�������
     * @return �ַ�������
     */
    public static String[] strToStrArray(String str) {
        return strToStrArrayManager(str, DEFAULT_PATH_SEPARATOR);
    }

    /**
     * ���ַ������󰴸����ķָ���separatorת��Ϊ�ַ����������
     * 
     * @param str  ��ת���ķ�������
     * @param separator �ַ��ͷָ���
     * @return �ַ�������
     */
    public static String[] strToStrArray(String str, String separator) {
        return strToStrArrayManager(str, separator);
    }

    private static String[] strToStrArrayManager(String str, String separator) {

        StringTokenizer strTokens = new StringTokenizer(str, separator);
        String[] strArray = new String[strTokens.countTokens()];
        int i = 0;

        while (strTokens.hasMoreTokens()) {
            strArray[i] = strTokens.nextToken().trim();
            i++;
        }

        return strArray;
    }

    public static Set<String> strToSet(String str) {
        return strToSet(str, DEFAULT_PATH_SEPARATOR);
    }

    public static Set<String> strToSet(String str, String separator) {
        String[] values = strToStrArray(str, separator);
        Set<String> result = new LinkedHashSet<String>();
        for (int i = 0; i < values.length; i++) {
            result.add(values[i]);
        }
        return result;
    }

    /**
     * ��һ�����飬�ö��ŷָ���Ϊһ���ַ���
     * @param array
     * @return modify by yuce reason:user StringBuffer replace "+"
     */
    public static String ArrayToStr(Object[] array) {
        if (array == null || array.length < 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (Object obj : array) {
            if (StringUtils.isNotBlank(obj.toString())) {
                if (sb.length() > 0) {
                    sb.append(DEFAULT_PATH_SEPARATOR);
                }
                sb.append(obj.toString());
            }
        }
        return sb.toString();
    }

    public static String CollectionToStr(Collection<String> coll) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (String string : coll) {
            if (i > 0) {
                sb.append(",");
            }
            i++;
            sb.append(string);
        }
        return sb.toString();
    }

    /**
     * ת�������ַ����ı����ʽ
     * @param inputString  ��ת���ַ�������
     * @param inencoding ��ת���ַ����ı����ʽ
     * @param outencoding  ׼��ת��Ϊ�ı����ʽ
     * @return ָ���������ַ������ַ�������
     */
    public static String encodingTransfer(String inputString, String inencoding, String outencoding) {
        if (inputString == null || inputString.length() == 0) {
            return inputString;
        }
        try {
            return new String(inputString.getBytes(outencoding), inencoding);
        } catch (Exception e) {
            return inputString;
        }
    }

    /**
     * ɾ���ַ�����ָ�����ַ� ֻҪ��delStrs�����г��ֵ��ַ���������inputString��Ҳ���ֶ��Ὣ����ɾ��
     * @param inputString ����ɾ��������ַ���
     * @param delStrs  ��Ϊɾ���ַ��Ĳ������ݣ��ö��ŷָ������Ҫɾ�����ſ���������ַ����ټ�һ������
     * @return ����һ����inputStringΪ��������delStrs�������ַ���
     */
    public static String delString(String inputString, String delStrs) {
        if (inputString == null || inputString.length() == 0) {
            return inputString;
        }
        String[] strs = strToStrArray(delStrs);
        for (int i = 0; i < strs.length; i++) {
            if ("".equals(strs[i])) {
                inputString = inputString.replaceAll(" ", "");
            } else {
                if (strs[i].compareTo("a") >= 0) {
                    inputString = replace(inputString, strs[i], "");
                } else {
                    inputString = inputString.replaceAll("\\" + strs[i], "");
                }
            }
        }
        if (subCount(delStrs, ",") > strs.length) {
            inputString = inputString.replaceAll("\\,", "");
        }
        return inputString;
    }

    /**
     * ȥ����߶���Ŀո�
     * @param value  ��ȥ��߿ո���ַ���
     * @return ȥ����߿ո����ַ���
     */
    public static String trimLeft(String value) {
        String result = value;
        if (result == null) {
            return result;
        }
        char[] ch = result.toCharArray();
        int index = -1;
        for (int i = 0; i < ch.length; i++) {
            if (Character.isWhitespace(ch[i])) {
                index = i;
            } else {
                break;
            }
        }
        if (index != -1) {
            result = result.substring(index + 1);
        }
        return result;
    }

    /**
     * ȥ���ұ߶���Ŀո�
     * @param value ��ȥ�ұ߿ո���ַ���
     * @return ȥ���ұ߿ո����ַ���
     */
    public static String trimRight(String value) {
        String result = value;
        if (result == null) {
            return result;
        }
        char[] ch = result.toCharArray();
        int endIndex = -1;
        for (int i = ch.length - 1; i > -1; i--) {
            if (Character.isWhitespace(ch[i])) {
                endIndex = i;
            } else {
                break;
            }
        }
        if (endIndex != -1) {
            result = result.substring(0, endIndex);
        }
        return result;
    }

    /**
     * �ж�һ���ַ������Ƿ������һ���ַ���
     * @param parentStr  ����
     * @param subStr  �Ӵ�
     * @return ������������Ӵ������ݷ����棬���򷵻ؼ�
     */
    public static boolean isInclude(String parentStr, String subStr) {
        return parentStr.indexOf(subStr) >= 0;
    }

    /**
     * �ж�һ���ַ������Ƿ������һ���ַ���������κ�һ��
     * @param parentStr    ����
     * @param subStrs  �Ӵ�����(�Զ��ŷָ����ַ���)
     * @return ������������Ӵ������ݷ����棬���򷵻ؼ�
     */
    public static boolean isIncludes(String parentStr, String subStrs) {
        String[] subStrArray = strToStrArray(subStrs);
        for (int j = 0; j < subStrArray.length; j++) {
            String subStr = subStrArray[j];
            if (isInclude(parentStr, subStr)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    /**
     * �ж�һ�����ַ����ڸ������ظ����ֵĴ���
     * @param parentStr ����
     * @param subStr  �Ӵ�
     * @return ���ַ����ڸ��ַ������صó��ֵĴ���
     */
    public static int subCount(String parentStr, String subStr) {
        int count = 0;

        for (int i = 0; i < ( parentStr.length() - subStr.length() ); i++) {
            String tempString = parentStr.substring(i, i + subStr.length());
            if (subStr.equals(tempString)) {
                count++;
            }
        }
        return count;
    }

    /**
     * �õ����ַ����дӿ�ʼ�ַ�������ֹ�ַ����м���Ӵ�
     * @param parentStr ����
     * @param startStr ��ʼ��
     * @param endStr ��ֹ��
     * @return ���ؿ�ʼ�����ֹ��֮����Ӵ�
     */
    public static String subString(String parentStr, String startStr, String endStr) {
        return parentStr.substring(parentStr.indexOf(startStr) + startStr.length(), parentStr.indexOf(endStr));
    }

    /**
     * �õ����ַ����дӿ�ʼ�ַ�������ֹ�ַ����м���Ӵ��ļ���
     * @param parentStr  ����
     * @param startStr ��ʼ��
     * @param endStr ��ֹ��
     * @return ���ؿ�ʼ�����ֹ��֮����Ӵ��ļ���
     */
    public static List<String> subStringList(String parentStr, String startStr, String endStr) {
        List<String> result = new ArrayList<String>();
        List<String> elements = dividToList(parentStr, startStr, endStr);
        for (String element : elements) {
            if (!isIncludes(element, startStr) || !isIncludes(element, endStr)) {
                continue;
            }
            result.add(subString(element, startStr, endStr));
        }
        return result;

    }

    /**
     * ��ָ����Stringת��ΪUnicode�ĵȼ�ֵ
     * ����֪ʶ�� ���е�ת���ַ������� '\' ��ͷ�ĵڶ����ַ� 0-9 :�˽��� u :��Unicodeת�⣬���ȹ̶�Ϊ6λ Other:��Ϊ������ĸ�е�һ�� b,t,n,f,r,",\ �������㣬�����һ���������
     * �ṩ�˽���Ҳ��Ϊ�˺�C���Լ���. b,t,n,f,r ����Ϊ�����ַ�.���ϵ���˼Ϊ:�����������ķ�����ϣ����Щ��Ϣ��α���ʽ�����߱���ʾ. ������д�ڴ��������λ�ã�ֻҪת����ǺϷ���. ����: int c=0\u003b
     * ����Ĵ�����Ա���ͨ������ͬ��int c=0; \u003bҲ����';'��Unicode����
     * 
     * @param str ��ת��ΪUnicode�ĵȼ��ַ���
     * @return Unicode���ַ���
     */
    public static String getUnicodeStr(String inStr) {
        char[] myBuffer = inStr.toCharArray();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < inStr.length(); i++) {
            byte b = (byte) myBuffer[i];
            short s = (short) myBuffer[i];
            String hexB = Integer.toHexString(b);
            String hexS = Integer.toHexString(s);
            /*
             * //���Ϊ��д String hexB = Integer.toHexString(b).toUpperCase(); String hexS =
             * Integer.toHexString(s).toUpperCase(); //print char sb.append("char["); sb.append(i); sb.append("]='");
             * sb.append(myBuffer[i]); sb.append("'\t");
             * 
             * //byte value sb.append("byte="); sb.append(b); sb.append(" \\u"); sb.append(hexB); sb.append('\t');
             */

            // short value
            sb.append(" \\u");
            sb.append(fillStr(hexS, "0", 4, AFTER));
            // sb.append('\t');
            // Unicode Block
            // sb.append(Character.UnicodeBlock.of(myBuffer[i]));
        }

        return sb.toString();

    }

    /**
     * �ж�һ���ַ����Ƿ��ǺϷ���Java��ʶ��
     * @param s ���жϵ��ַ���
     * @return �������s��һ���Ϸ���Java��ʶ�������棬���򷵻ؼ�
     */
    public static boolean isJavaIdentifier(String s) {
        if (s.length() == 0 || Character.isJavaIdentifierStart(s.charAt(0))) {
            return false;
        }
        for (int i = 0; i < s.length(); i++)
            if (!Character.isJavaIdentifierPart(s.charAt(i))) {
                return false;
            }
        return true;
    }

    /**
     * �滻�ַ����������������ַ� ����: replaceAll("\com\hi\platform\base\\util",'\\','/'); ���صĽ��Ϊ: /com/hi/platform/base/util
     * @param str ���滻���ַ���
     * @param oldchar   ���滻���ַ�
     * @param newchar �滻Ϊ���ַ�
     * @return ��str�е�����oldchar�ַ�ȫ���滻Ϊnewchar,����������滻����ַ���
     */
    public static String replaceAll(String str, char oldchar, char newchar) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == oldchar) {
                chars[i] = newchar;
            }
        }
        return new String(chars);
    }

    /**
     * ���inStr�ַ�����û�и���length�ĳ��ȣ�����fill��䣬��ָ��direction�ķ��� ���inStr�ַ������ȴ���length��ֱ�ط���inStr����������
     * 
     * @param inStr
     *            ��������ַ���
     * @param fill
     *            �Ը��ַ�����Ϊ����ַ���
     * @param length
     *            ����Ҫ��ĳ���
     * @param direction
     *            ��䷽�������AFTER����ں��棬���������ǰ��
     * @return ����һ��ָ������������ַ���
     */
    public static String fillStr(String inStr, String fill, int length, int direction) {
        if (inStr == null || inStr.length() > length || inStr.length() == 0) {
            return inStr;
        }
        StringBuffer tempStr = new StringBuffer("");
        for (int i = 0; i < length - inStr.length(); i++) {
            tempStr.append(fill);
        }

        if (direction == AFTER) {
            return new String(tempStr.toString() + inStr);
        } else {
            return new String(inStr + tempStr.toString());
        }
    }

    /**
     * �ַ����滻
     * 
     * @param str
     *            Դ�ַ���
     * @param pattern
     *            ���滻���ַ���
     * @param replace
     *            �滻Ϊ���ַ���
     * @return
     */
    public static String replace(String str, String pattern, String replace) {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();
        while ( ( e = str.indexOf(pattern, s) ) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e + pattern.length();
        }
        result.append(str.substring(s));

        return result.toString();
    }

    /**
     * �ָ��ַ�����һ������
     * 
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static List<String> dividToList(String str, String start, String end) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int s = 0;
        int e = 0;
        List<String> result = new ArrayList<String>();
        if (isInclude(str, start) && isInclude(str, end)) {
            while ( ( e = str.indexOf(start, s) ) >= 0) {
                result.add(str.substring(s, e));
                s = str.indexOf(end, e) + end.length();
                result.add(str.substring(e, s));
            }
            if (s < str.length()) {
                result.add(str.substring(s));
            }
            if (s == 0) {
                result.add(str);
            }
        } else {
            result.add(str);
        }
        return result;
    }

    /**
     * ����ĸ��д
     * 
     * @param string
     * @return
     */
    public static String upperFrist(String string) {
        if (string == null) {
            return null;
        }
        String upper = string.toUpperCase();
        return upper.substring(0, 1) + string.substring(1);
    }

    /**
     * ����ĸСд
     * 
     * @param string
     * @return
     */
    public static String lowerFrist(String string) {
        if (string == null) {
            return null;
        }
        String lower = string.toLowerCase();
        return lower.substring(0, 1) + string.substring(1);
    }

    public static String URLEncode(String string, String encode) {
        try {
            return URLEncoder.encode(string, encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ��һ���������͵Ķ���ת��Ϊָ����ʽ���ַ���
     * 
     * @param date
     *            ��ת��������
     * @param format
     *            ת��Ϊ�ַ�������Ӧ��ʽ ���磺DateToStr(new Date() ,"yyyy.MM.dd G 'at' hh:mm:ss a zzz");
     * @return һ���ַ���
     *         <p>
     * 
     *         <table border="0" cellspacing="3" cellpadding="0">
     *         <tr bgcolor="#ccccff">
     *         <th align="left">Letter
     *         <th align="left">Date or Time Component
     *         <th align="left">Presentation
     *         <th align="left">Examples
     *         <tr>
     *         <td><code>G</code>
     *         <td>Era designator
     *         <td><a href="#text">Text</a>
     *         <td><code>AD</code>
     *         <tr bgcolor="#eeeeff">
     *         <td>Year
     *         <td><a href="#year">Year</a>
     *         <td><code>1996</code>; <code>96</code>
     *         <tr>
     *         <td><code>M</code>
     *         <td>Month in year
     *         <td><a href="#month">Month</a>
     *         <td><code>July</code>; <code>Jul</code>; <code>07</code>
     *         <tr bgcolor="#eeeeff">
     *         <td><code>w</code>
     *         <td>Week in year
     *         <td><a href="#number">Number</a>
     *         <td><code>27</code>
     *         <tr>
     *         <td><code>W</code>
     *         <td>Week in month
     *         <td><a href="#number">Number</a>
     *         <td><code>2</code>
     *         <tr bgcolor="#eeeeff">
     *         <td><code>D</code>
     *         <td>Day in year
     *         <td><a href="#number">Number</a>
     *         <td><code>189</code>
     *         <tr>
     *         <td><code>d</code>
     *         <td>Day in month
     *         <td><a href="#number">Number</a>
     *         <td><code>10</code>
     *         <tr bgcolor="#eeeeff">
     *         <td><code>F</code>
     *         <td>Day of week in month
     *         <td><a href="#number">Number</a>
     *         <td><code>2</code>
     *         <tr>
     *         <td><code>E</code>
     *         <td>Day in week
     *         <td><a href="#text">Text</a>
     *         <td><code>Tuesday</code>; <code>Tue</code>
     *         <tr bgcolor="#eeeeff">
     *         <td><code>a</code>
     *         <td>Am/pm marker
     *         <td><a href="#text">Text</a>
     *         <td><code>PM</code>
     *         <tr>
     *         <td><code>H</code>
     *         <td>Hour in day (0-23)
     *         <td><a href="#number">Number</a>
     *         <td><code>0</code>
     *         <tr bgcolor="#eeeeff">
     *         <td><code>k</code>
     *         <td>Hour in day (1-24)
     *         <td><a href="#number">Number</a>
     *         <td><code>24</code>
     *         <tr>
     *         <td><code>K</code>
     *         <td>Hour in am/pm (0-11)
     *         <td><a href="#number">Number</a>
     *         <td><code>0</code>
     *         <tr bgcolor="#eeeeff">
     *         <td><code>h</code>
     *         <td>Hour in am/pm (1-12)
     *         <td><a href="#number">Number</a>
     *         <td><code>12</code>
     *         <tr>
     *         <td><code>m</code>
     *         <td>Minute in hour
     *         <td><a href="#number">Number</a>
     *         <td><code>30</code>
     *         <tr bgcolor="#eeeeff">
     *         <td><code>s</code>
     *         <td>Second in minute
     *         <td><a href="#number">Number</a>
     *         <td><code>55</code>
     *         <tr>
     *         <td><code>S</code>
     *         <td>Millisecond
     *         <td><a href="#number">Number</a>
     *         <td><code>978</code>
     *         <tr bgcolor="#eeeeff">
     *         <td><code>z</code>
     *         <td>Time zone
     *         <td><a href="#timezone">General time zone</a>
     *         <td><code>Pacific Standard Time</code>; <code>PST</code>; <code>GMT-08:00</code>
     *         <tr>
     *         <td><code>Z</code>
     *         <td>Time zone
     *         <td><a href="#rfc822timezone">RFC 822 time zone</a>
     *         <td><code>-0800</code>
     *         </table>
     */
    public static String DateToStr(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * �Ը������ַ�����htmlת��
     * 
     * @param string
     * @return
     */
    public static String escapeHtml(String string) {
        if (string == null || string.length() == 0) {
            return "";
        }

        char b;
        char c = 0;
        int i;
        int len = string.length();
        StringBuffer sb = new StringBuffer(len + 4);
        String t;

        for (i = 0; i < len; i += 1) {
            b = c;
            c = string.charAt(i);
            switch (c) {
            case '\\':
            case '"':
                sb.append('\\');
                sb.append(c);
                break;
            case '/':
                if (b == '<') {
                    sb.append('\\');
                }
                sb.append(c);
                break;
            case '\b':
                sb.append("\\b");
                break;
            case '\t':
                sb.append("\\t");
                break;
            case '\n':
                sb.append("\\n");
                break;
            case '\f':
                sb.append("\\f");
                break;
            case '\r':
                sb.append("\\r");
                break;
            default:
                if (c < ' ' || ( c >= '\u0080' && c < '\u00a0' ) || ( c >= '\u2000' && c < '\u2100' )) {
                    t = "000" + Integer.toHexString(c);
                    sb.append("\\u" + t.substring(t.length() - 4));
                } else {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }

    /**
     * ����һ��ָ�����ȵ�����ַ���
     * 
     * @param length
     *            ���ص��ַ�������
     * @return ����һ�����
     */
    public static String randomString(int length) {
        if (length < 1) {
            return null;
        }
        Random randGen = new Random();
        char[] numbersAndLetters = ( "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" ).toCharArray();
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(51)];
        }
        return new String(randBuffer);
    }

    public final static String trim(String target) {
        if (target == null)
            return null;

        target = target.trim();

        return "".equals(target) ? null : target;
    }

    /**
     * string to int
     * 
     * @param numStr
     * @return
     */
    public static int strToInt(String numStr) {
        try {
            if (isNullOrBlank(numStr)) {
                return 0;
            }
            return Integer.valueOf(numStr);
        } catch (Exception e) {
//            logger.error("str2int failed:", e);
        }
        return 0;
    }

    /**
     * �ж�null �� ���ַ���
     * 
     * @param str
     * @return
     */
    public static boolean isNullOrBlank(String str) {
        if (str == null) {
            return true;
        }
        str = str.trim();
        if (!str.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * �ж� ���ַ���
     * 
     * @param value
     * @return
     */
    public static boolean isBlank(String value) {
        boolean ret = false;
        if ( ( value != null ) && ( value.equals("") )) {
            ret = true;
        }
        return ret;
    }

    /**
     * �ж�null
     */
    public static boolean isNull(Object value) {
        return ( value == null );
    }

    /**
     * Title : ��ȡ�����
     * 
     * @author : ��ˮ��
     * @param digits
     *            �����ȡֵ��Χ Ĭ��Ϊ0123456789
     * @param length
     *            �����λ�� Ĭ��Ϊ1λ
     * @return
     */
    public static String getRandom(String digits, int length) {
        if (null == digits) {
            digits = "0123456789";
        }
        if (length <= 0) {
            length = 1;
        }
        char[] code = new char[length];
        String temp = "";
        for (int i = 0; i < length; i++) {
            code[i] = digits.charAt((int) ( Math.random() * digits.length() ));
            temp += "0";
        }
        String verifyCode = new String(code);

        if (verifyCode.equals(temp)) {
            verifyCode = getRandom(digits, length);
        }
        return verifyCode;
    }

    /**
     * Title : ��ȡ��Χ��min��max��������
     * 
     * @author : ��ˮ��
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min, int max) {
        return (int) ( Math.random() * ( max - min ) + min );
    }

    /**
     * Title : ��ȡ��Χ��min��max���num�������
     * 
     * @author : ��ˮ��
     * @param min
     * @param max
     * @return
     */
    public static Integer[] getRandomNum(int min, int max, int num) {
        Integer[] result = new Integer[num];
        for (int i = 0; i < num; i++) {
            result[i] = getRandom(min, max);
        }

        for (int i = 0; i < num; i++) {
            for (int k = i + 1; k < num; k++) {
                if (result[i] == result[k]) {
                    getRandomNum(min, max, num);
                }
            }
        }
        return result;
    }

    public static String ifBlank(String... ss) {
        String ret = "";
        for (String s : ss) {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(s)) {
                ret = s;
                break;
            }
        }
        return ret;
    }

    public static String getUrlParamters(String url, String prefix) {
        String _return = null;
        if (url.indexOf("?") >= 0) {
            url = url.substring(url.indexOf("?") + 1);
            String[] paramters = url.split("&");
            if (paramters != null) {
                for (String s : paramters) {
                    if (s.startsWith(prefix)) {
                        String[] _temp = s.split("=");
                        if (_temp.length > 1) {
                            _return = _temp[1];
                        }
                    }
                }
            }
        }
        return _return;

    }

    /**
     * �ַ���ת����Long������
     * 
     * @param src
     * @return
     * 
     */
    public static long toLong(String src) {
        long dest = 0;
        if (src == null || src.trim().equals("")) {
            return 0;
        }

        try {
            dest = Long.parseLong(src.trim());
        } catch (Exception e) {
        }
        return dest;
    }

    /**
     * ����*Info���͵��ַ�����һ��������Ϣ��ѯ��ӿڵķ��ء�
     * 
     * ���磬����buildInfoString("#",aaa,bbb,null,ddd); �õ��ַ�����aaa#bbb##ddd
     * 
     * @param delimiter
     * @param items
     * @return
     */
    public static String buildInfoString(String delimiter, Object... items) {
        StringBuffer buff = new StringBuffer();
        boolean firstItem = true;
        for (Object item : items) {
            if (firstItem) {
                firstItem = false;
            } else {
                buff.append(delimiter);
            }

            if (item == null) {
                buff.append("");
            } else {
                buff.append(item.toString());
            }

        }

        return buff.toString();
    }

    public static long yuan2Cent(String src) {
        if (src == null || src.trim().equals("")) {
            return 0;
        }

        long ret = 0;
        try {
            ret = (long) ( Math.round(Double.parseDouble(src) * 100) );
        } catch (Exception e) {
        }

        return ret;
    }

    /**
     * �ѷ�ת��ΪԪ
     * 
     * @param src
     * @param floor
     *            �Ƿ�ȡ��
     * @return
     */
    public static String cent2Yuan(String src, boolean floor) {

        long temp = 0;
        try {
            String tem = src.trim();
            temp = Long.parseLong(tem);
        } catch (Exception e) {
        }
        return cent2Yuan(temp, floor);
    }

    public static String cent2Yuan(long src, boolean floor) {
        DecimalFormat SDF_YUAN = new DecimalFormat("0.00");
        String yuan = "0.00";
        try {
            yuan = SDF_YUAN.format(src / 100.00);
        } catch (Exception e) {
        }

        if (floor) {
            int idxDot = yuan.indexOf(".");
            if (idxDot >= 0) {
                yuan = yuan.substring(0, idxDot);
            }
        }
        return yuan;

    }

    public static String cent2Yuan(Double src, boolean floor) {
        DecimalFormat SDF_YUAN = new DecimalFormat("0.00");
        String yuan = "0.00";
        try {
            yuan = SDF_YUAN.format(src / 100.00);
        } catch (Exception e) {
        }

        if (floor) {
            int idxDot = yuan.indexOf(".");
            if (idxDot >= 0) {
                yuan = yuan.substring(0, idxDot);
            }
        }
        return yuan;

    }

    /**
     * 
     * ��һ���ַ������ָ����ֳɶ���Ӵ��� �˷������ڴ���Jdk��String.split()��������ͬ�ĵط���<br>
     * (1)�ڴ˷����У����ַ����������ĸ�λ�ö���Ϊһ����Ч�ִ��� <br>
     * (2)�ڴ˷����У��ָ�����������һ��������ʽ��<br>
     * 
     * @param src
     *            Դ�ַ���
     * @param delimiter
     *            �ָ���
     * @return �ַ������顣
     */
    public static String[] split(String src, String delimiter) {
        if (src == null || delimiter == null || src.trim().equals("") || delimiter.equals("")) {
            return new String[] { src };
        }

        ArrayList<String> list = new ArrayList<String>();

        int lengthOfDelimiter = delimiter.length();
        int pos = 0;
        while (true) {
            if (pos < src.length()) {

                int indexOfDelimiter = src.indexOf(delimiter, pos);
                if (indexOfDelimiter < 0) {
                    list.add(src.substring(pos));
                    break;
                } else {
                    list.add(src.substring(pos, indexOfDelimiter));
                    pos = indexOfDelimiter + lengthOfDelimiter;
                }
            } else if (pos == src.length()) {
                list.add("");
                break;
            } else {
                break;
            }
        }

        String[] result = new String[list.size()];
        list.toArray(result);
        return result;

    }

    /**
     * ��ԭ����ΪNull�򷵻ؿ��ַ���,������תΪString���ͣ��ټ�ȥ�ַ������˵Ŀո񼰿����ַ�
     * 
     * @param src
     * @return
     */
    public static String trim(Object src) {
        if (src == null) {
            return "";
        }

        String str = src.toString();
        return str.trim();
    }

    /**
     * ��һ������key1=value1&key2=value2...���ַ���ת����Mapӳ�䡣
     * 
     * @param src
     * @return
     * 
     */
    public static Map<String, String> string2Map(String src) {
        return string2Map(src, String.class, String.class, "&", "=");
    }

    /**
     * ��һ������key1=value1&key2=value2...���ַ���ת����Mapӳ�䡣 ע�⣺key��valueֻ֧������ΪString,Long,Integer,Float,Double��
     * 
     * @param <K>
     * @param <V>
     * @param src
     *            Դ�ַ���
     * @param keyClass
     *            ���ɵ�Map��Key�����ͣ�Ĭ��String
     * @param valueClass
     *            ���ɵ�Map��Value�����ͣ�Ĭ��String
     * @param fieldDelimiter
     *            �ֶ����ֶ�֮��ķָ�����Ĭ��&
     * @param keyValueDelimiter
     *            key��value֮��ķָ�����Ĭ��=
     * @return
     * 
     */
    public static <K extends Object, V extends Object> Map<K, V> string2Map(String src, Class<K> keyClass, Class<V> valueClass,
            String fieldDelimiter, String keyValueDelimiter) {
        Map<K, V> result = new HashMap<K, V>();

        if (src == null || src.trim().equals("")) {
            return result;
        }

        String[] fields = StringHelper.split(src, fieldDelimiter);
        for (int i = 0; i < fields.length; i++) {
            String[] keyValue = StringHelper.split(fields[i], keyValueDelimiter);
            String key = keyValue[0];
            String value = keyValue[1];

            K objKey = null;
            V objValue = null;

            if (keyClass == String.class) {
                objKey = (K) key;
            } else if (keyClass == Integer.class) {
                objKey = (K) Integer.valueOf(key);
            } else if (keyClass == Long.class) {
                objKey = (K) Long.valueOf(key);
            } else if (keyClass == Float.class) {
                objKey = (K) Float.valueOf(key);
            } else if (keyClass == Double.class) {
                objKey = (K) Double.valueOf(key);
            } else {
                return null;
            }

            if (valueClass == String.class) {
                objValue = (V) value;
            } else if (valueClass == Integer.class) {
                objValue = (V) Integer.valueOf(value);
            } else if (valueClass == Long.class) {
                objValue = (V) Long.valueOf(value);
            } else if (valueClass == Float.class) {
                objValue = (V) Float.valueOf(value);
            } else if (valueClass == Double.class) {
                objValue = (V) Double.valueOf(value);
            } else {
                return null;
            }

            result.put(objKey, objValue);

        }

        return result;
    }

    /**
     * Mapת�����ַ�������Ҫ���ڴ�ӡ������Ϣ
     * 
     * @param map
     * @return
     */
    public static String map2String(Map map) {
        return map2String(map, "", "", "", true, "=");
    }

    /**
     * Mapת�����ַ�������Ҫ���ڴ�ӡ������Ϣ
     * 
     * @param map
     * @param head
     *            �����ͷ
     * @param entryPrefix
     *            ÿһ�������ǰ׺
     * @param foot
     *            ����Ľ�
     * @param isOneItemPl
     *            �Ƿ�ÿ��һ��
     * @param kvSep
     *            Key��Value�ķָ���
     * @return
     */
    public static String map2String(Map map, String head, String entryPrefix, String foot, boolean isOneItemPl, String kvSep) {

        if (map == null) {
            return null;
        }
        String lineSeparator = (String) System.getProperty("line.separator");
        StringBuffer buff = new StringBuffer();
        if (head != null && !head.equals("")) {
            buff.append(head);

            if (isOneItemPl) {
                buff.append(lineSeparator);
            }
        }
        if (entryPrefix == null) {
            entryPrefix = "";
        }
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
            Entry entry = (Entry) iterator.next();
            buff.append(entryPrefix).append(entry.getKey()).append(kvSep).append(StringHelper.ArrayToStr((Object[]) entry.getValue()));

            if (isOneItemPl) {
                buff.append(lineSeparator);
            }
        }

        if (foot != null && !foot.equals("")) {
            buff.append(foot);
            if (isOneItemPl) {
                buff.append(lineSeparator);
            }
        }
        return buff.toString();
    }

    /**
     * �������0
     * 
     * @param src
     * @param targetLength
     * @return
     */
    public static String fill(long src, int targetLength) {
        return fill(String.valueOf(src), "0", targetLength, true);
    }

    /**
     * ����ַ��������ԭ�ַ�����Ŀ�곤�ȳ������ȥ����Ĳ��֡����onTheLeft����true ��ȥ��߲��֣������ȥ�ұ߲��֡� ע�������һ��Ϊ�����ַ�������Ƕ���ַ������ܵ������Ľ�������á�
     * 
     * @param src
     *            ԭ�ַ���
     * @param padding
     *            ����һ����0���ո��
     * @param targetLength
     *            Ŀ�곤��
     * @param onTheLeft
     *            �Ƿ�����䡣
     * @return
     */
    public static String fill(String src, String padding, int targetLength, boolean onTheLeft) {

        if (src == null) {
            src = "";
        }

        while (src.length() < targetLength) {
            if (onTheLeft) {
                src = padding + src;
            } else {
                src = src + padding;
            }
        }

        if (src.length() > targetLength) {
            if (onTheLeft) {
                src = src.substring(src.length() - targetLength);
            } else {
                src = src.substring(0, targetLength);
            }
        }

        return src;
    }

    public static String changeListToString(List<String> source, String delimiter) {
        StringBuilder builder = new StringBuilder();
        if (source != null && source.size() > 0) {
            int len = source.size();
            for (int i = 0; i < len; i++) {
                builder.append(source.get(i));
                if (i < len - 1) {
                    builder.append(delimiter);
                }

            }
        }
        return builder.toString();
    }

    public static String changeListToStringWithTag(List<String> source, String delimiter, String tag) {
        StringBuilder builder = new StringBuilder();
        if (source != null && source.size() > 0) {
            int len = source.size();
            for (int i = 0; i < len; i++) {
                builder.append(tag + source.get(i) + tag);
                if (i < len - 1) {
                    builder.append(delimiter);
                }

            }
        }
        return builder.toString();
    }

    /**
     * �Ƿ����null�����߿��ַ���������һ��������������������true�����򷵻�false��<br>
     * �Ƚ���������ת���ַ������޼�������жϡ��������ո��ASCII�����ַ�Ҳ��Ϊ�������㡣<br>
     * 
     * Noe:Null Or Empty<br>
     * 
     * @param strings
     * @return
     */
    public static boolean existNoe(Object... someObj) {
        if (someObj == null || someObj.length == 0) {
            throw new RuntimeException("��������Ϊ��,����������һ������");
        }

        for (int i = 0; i < someObj.length; i++) {
            Object obj = someObj[i];
            if (obj == null || obj.toString().trim().equals("")) {
                return true;
            }
        }

        return false;

    }

    /**
     * ��ԭ�ַ���ΪNull�򷵻ؿ��ַ�����
     * 
     * @param src
     * @return
     */
    public static String null2Empty(String src) {
        if (src == null) {
            return "";
        }
        return src;
    }

    /**
     * ��ԭ�ַ���ΪNull�򷵻ؿ��ַ�����
     * 
     * @param src
     * @return
     */
    public static boolean isEmpty(String src) {
        String value = null2Empty(src);
        if ("".equals(value)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * �Ƿ�ȫ���ǿ�
     * 
     * @param src
     * @return
     */
    public static boolean isAllNotEmpty(String... src) {
        for (int i = 0; i < src.length; i++) {
            String value = src[i];
            if (value == null || value.equals("")) {
                return false;
            }
        }

        return true;
    }

    /**
     * �Ƿ�ȫ��Ϊ��
     * 
     * @param src
     * @return
     */
    public static boolean isAllEmpty(String... src) {
        for (int i = 0; i < src.length; i++) {
            String value = src[i];
            if (value != null && !value.equals("")) {
                return false;
            }
        }

        return true;
    }

    /**
     * �Ƿ�ȫΪ��ĸ������
     * 
     * @param src
     * @return
     */
    public static boolean isLetterOrNumber(String src) {
        if (src == null) {
            return false;
        }

        try {
            byte[] bytesOfSrc = src.getBytes("utf-8");

            for (int i = 0; i < bytesOfSrc.length; i++) {
                byte one = bytesOfSrc[i];
                if (one < '0' || ( one > '9' && one < 'A' ) || ( one > 'Z' && one < 'a' ) || one > 'z') {
                    return false;
                }
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static String privacyInfo(String currString) {

        String targetString = "";
        if (!StringHelper.isBlank(currString)) {
            if (currString.length() <= 3) {
                targetString = currString.replace(org.apache.commons.lang3.StringUtils.substring(currString, 0, 1), "*");
            } else if (currString.length() > 3) {
                targetString = currString.replace(org.apache.commons.lang3.StringUtils.substring(currString, 0, 2), "*");
            }
        }

        return targetString;
    }

    /**
     * �Ƚ������ַ����Ƿ����
     * 
     * @param one
     * @param another
     * @return
     */
    public static boolean equals(String one, String another) {
        if (one == null) {
            if (another == null) {
                return true;
            } else {
                return false;
            }
        } else {
            if (another == null) {
                return false;
            } else {
                return one.equals(another);
            }
        }
    }
    
    /**
     * ��ȡ�ַ���
     * @param value �ַ���
     * @param maxLen ����ַ�������
     * @return
     */
    public static String getSubStr(String value,int maxLen){
        if (value.length() > maxLen) {
            value = value.substring(0, maxLen);
        }
        return value;
    }
    
}