/**
 * 
 */
package com.netPoint.applications.site.factory;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Faliherizo
 *
 */
public class HTMLEncoder {

  private static final String   FORMAT_ISO_8859_1 = "ISO-8859-1";
  private static final String   FORMAT_UTF_8      = "UTF-8";

  @SuppressWarnings("unchecked")
  private static Map            mapChar2HTMLEntity;
  // 'Ü', 'Ä', 'Ö', 'Ë', 'Ç', 'Æ', 'Å', 'Ø',
  // 'ü', 'ä', 'ö', 'ë', 'ç', 'å', 'ø', '`',
  // 'à', 'è', 'ì', 'ò', 'ù', ('&'), 'ß', ' ',
  // ('>','<',)'©', '¢', '£', '«', '»', '®',
  // '-', ('\''), 'á', 'ú', 'ó', 'é', 'í', 'ñ',
  // '§', 'è', 'î', 'ô', 'â', 'û', 'ê', 'æ',
  // '¡', ('\"'), 'ª', '×', '°', '', '¦'
  private static final char[]   CHARACTERS        = { '\u00DC', '\u00C4', '\u00D6', '\u00CB', '\u00C7', '\u00C6', '\u00C5', '\u00D8', '\u00FC', '\u00E4',
      '\u00F6', '\u00EB', '\u00E7', '\u00E5', '\u00F8', '\u0060', '\u00E0', '\u00E8', '\u00EC', '\u00F2', '\u00F9', '\u00DF', '\u00A0', '\u009A', '\u00A2',
      '\u00A3', '\u00AB', '\u00BB', '\u00AE', '\u00AD', '\u00E1', '\u00FA', '\u00F3', '\u00E9', '\u00ED', '\u00F1', '\u00A7', '\u00E8', '\u00EE', '\u00F4',
      '\u00E2', '\u00FB', '\u00EA', '\u00E6', '\u00A1', '\u00AA', '\u00D7', '\u00B0', '\u20AC', '\u007C', '\u00B5', '\u00BA', '\u00F7', '\u00B2', '\u00B3',
      '\u00B1'                                   };
  private static final String[] ENTITIES          = { "&Uuml;", "&Auml;", "&Ouml;", "&Euml;", "&Ccedil;", "&AElig;", "&Aring;", "&Oslash;", "&uuml;", "&auml;",
      "&ouml;", "&euml;", "&ccedil;", "&aring;", "&oslash;", "&grave;", "&agrave;", "&egrave;", "&igrave;", "&ograve;", "&ugrave;", "&szlig;", "&nbsp;",
      "&copy;", "&cent;", "&pound;", "&laquo;", "&raquo;", "&reg;", "&middot;", "&aacute;", "&uacute;", "&oacute;", "&eacute;", "&iacute;", "&ntilde;",
      "&sect;", "&egrave;", "&icirc;", "&ocirc;", "&acirc;", "&ucirc;", "&ecirc;", "&aelig;", "&iexcl;", "&ordf;", "&times;", "&deg;", "&euro;", "&brvbar;",
      "&micro;", "&ordm;", "&divide", "&sup2;", "&sup3;", "&plusmn;" };

  @SuppressWarnings("unchecked")
  public HTMLEncoder() {
    mapChar2HTMLEntity = new HashMap();
    int longueur = CHARACTERS.length;
    for (int i = 0; i < longueur; i++) {
      mapChar2HTMLEntity.put(new Character(CHARACTERS[i]), ENTITIES[i]);
    }
  }

  public String encode(String s) {
    int longueur = s.length();
    final StringBuffer sb = new StringBuffer(longueur * 2);
    char ch;
    for (int i = 0; i < longueur; ++i) {
      ch = s.charAt(i);
      if ((ch >= 63 && ch <= 90) || (ch >= 97 && ch <= 122)) {
        sb.append(ch);
      }
      else {
        String ss = (String) mapChar2HTMLEntity.get(new Character(ch));
        if (ss == null) {
          sb.append(ch);
        }
        else {
          sb.append(ss);
        }
      }
    }
    return sb.toString();
  }

  /***
   * Encode caractere en format ISO-8859-1
   * 
   * @param stringToEncode
   *          chaine avec des caractere encoder en UTF-8
   * @return
   * @throws UnsupportedEncodingException
   */
  public String encodeIsoFormat(String stringToEncode) {
    byte[] iso88591Data = null;
    try {
      iso88591Data = stringToEncode.getBytes(FORMAT_ISO_8859_1);
    } catch (UnsupportedEncodingException e) {
    }
    return new String(iso88591Data);
  }

  public String encodeIsoToUtf8Format(String stringToEncode) {
    Charset iso = Charset.forName(FORMAT_ISO_8859_1);
    Charset utf8 = Charset.forName(FORMAT_UTF_8);
    return new String(stringToEncode.getBytes(iso), utf8);
  }

  /***
   * Encode caractere en format UTF-8
   * 
   * @param stringToEncode
   *          chaine avec des caractere encoder en UTF-8
   * @return
   * @throws UnsupportedEncodingException
   */
  public String encodeUtf8Format(String stringToEncode) {
    byte[] utf8Data = null;
    String strUtf8 = stringToEncode;
    try {
      utf8Data = stringToEncode.getBytes(FORMAT_UTF_8);
      strUtf8 = new String(utf8Data, FORMAT_UTF_8);
    } catch (UnsupportedEncodingException e) {
    }
    return strUtf8;
  }

  /***
   * Encode caractere en format UTF-8
   * 
   * @param stringToEncode
   *          chaine avec des caractere encoder en UTF-8
   * @return
   * @throws UnsupportedEncodingException
   */
  public byte[] encodeUtf8(String stringToEncode) {
    byte[] utf8Data = null;
    try {
      utf8Data = stringToEncode.getBytes(FORMAT_UTF_8);
    } catch (UnsupportedEncodingException e) {
    }
    return utf8Data;
  }
}
