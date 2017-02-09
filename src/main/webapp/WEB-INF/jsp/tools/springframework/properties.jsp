<%@ page session="false"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.springframework.beans.factory.config.PropertiesFactoryBean"%>
<%@page import="java.util.Properties"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="org.springframework.core.io.Resource"%>
<%@page import="org.springframework.web.context.support.ServletContextResource"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.regex.Matcher"%>

<%@page import="org.apache.commons.lang.StringUtils"%><html>
<head>
<title>Resolved properties</title>
</head>
<body>
<div id="container">
<h1>Resolved properties</h1>
<pre>
<%  
    try {
        String user = System.getProperty("user");
        out.println("<h2>" + user + "</h2>");
        Resource[] locations = new Resource[] {
        		new ServletContextResource(pageContext.getServletContext(), "WEB-INF/config/common.properties"),
        		new ServletContextResource(pageContext.getServletContext(), "WEB-INF/config/" + user + ".properties")
        	};
        
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setSingleton(false);
        propertiesFactoryBean.setLocations(locations);
        
        Properties properties = (Properties) propertiesFactoryBean.getObject();
		
		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
		    String propertyName = (String) entry.getKey();
			String propertyValue = (String) entry.getValue();
			String convertedValue = resolveProperties(propertyValue, properties);
			if (!StringUtils.equals(propertyValue, convertedValue)) {
			    properties.setProperty(propertyName, convertedValue);
			}
		}
        
		@SuppressWarnings("unchecked")
		List keys = new ArrayList(properties.keySet());
        Collections.sort(keys); 
        for(Object key : keys) {
	        out.println(key + " = <span style=\"color:blue;\">" + properties.get(key) + "</span>");
        }
    } catch (Exception e) {
        PrintWriter printWriter = new PrintWriter(out);
        e.printStackTrace(printWriter);
    }
%>
<%!
	private String resolveProperties(String string, Properties properties) {
	    if (string == null) {
	        return null;
	    }
	    String propertyRegExp = "\\$\\{(.*?)\\}";
	    Pattern p = Pattern.compile(propertyRegExp);
	    Matcher m = p.matcher(string);
	    if (m.find()) {
	        String replacement = resolveProperties(properties.getProperty(m.group(1)), properties);
	        string = m.replaceAll(replacement);
	    }
	    return string;
	}
%>
</pre>
</div>
</body>
</html>