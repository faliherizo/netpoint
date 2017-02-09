/**
 * 
 * <?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <LogIn xmlns="http://tempuri.org/">
      <login>string</login>
      <password>string</password>
      <IdExtSite>int</IdExtSite>
    </LogIn>
  </soap:Body>
</soap:Envelope>
*
*Schema XML register commmand
*<wsdl:definitions targetNamespace="http://tempuri.org/">
*<wsdl:types>
*<s:schema elementFormDefault="qualified" targetNamespace="http://tempuri.org/">
*<s:element name="register"><s:complexType>
*<s:sequence>
*<s:element minOccurs="0" maxOccurs="1" name="header" type="tns:Header"/>
*<s:element minOccurs="0" maxOccurs="1" name="lines" type="tns:ArrayOfLine"/>
*</s:sequence></s:complexType></s:element><s:complexType name="Header">
*<s:sequence><s:element minOccurs="1" maxOccurs="1" name="IdExtSite" type="s:int"/>
*<s:element minOccurs="0" maxOccurs="1" name="IdClienteExt" type="s:string"/>
*<s:element minOccurs="1" maxOccurs="1" name="Accion" type="s:int"/>
*<s:element minOccurs="0" maxOccurs="1" name="Nombre" type="s:string"/>
*<s:element minOccurs="0" maxOccurs="1" name="Apellidos" type="s:string"/>
*<s:element minOccurs="0" maxOccurs="1" name="Compania" type="s:string"/>
*<s:element minOccurs="0" maxOccurs="1" name="Direccion" type="s:string"/>
*<s:element minOccurs="0" maxOccurs="1" name="Localidad" type="s:string"/>
*<s:element minOccurs="0" maxOccurs="1" name="Region" type="s:string"/>
*<s:element minOccurs="0" maxOccurs="1" name="CP" type="s:string"/>
*<s:element minOccurs="0" maxOccurs="1" name="IdPaisISOCode" type="s:string"/>
*<s:element minOccurs="0" maxOccurs="1" name="Telefono" type="s:string"/>
*<s:element minOccurs="0" maxOccurs="1" name="Email" type="s:string"/>
*</s:sequence></s:complexType><s:complexType name="ArrayOfLine">
*<s:sequence><s:element minOccurs="0" maxOccurs="unbounded" name="Line" nillable="true" type="tns:Line"/>
*</s:sequence></s:complexType><s:complexType name="Line"><s:sequence>
*<s:element minOccurs="0" maxOccurs="1" name="CodProducto" type="s:string"/>
*<s:element minOccurs="1" maxOccurs="1" name="Cantidad" type="s:int"/>
*<s:element minOccurs="1" maxOccurs="1" name="MO_Precio" type="s:float"/>
*<s:element minOccurs="1" maxOccurs="1" name="IdDivisa" type="s:int"/>
*<s:element minOccurs="0" maxOccurs="1" name="CodProductoOrigen" type="s:string"/>
*</s:sequence></s:complexType><s:element name="registerResponse">
*<s:complexType>
*<s:sequence><s:element minOccurs="0" maxOccurs="1" name="registerResult" type="s:string"/>
*</s:sequence></s:complexType></s:element><s:element name="LogIn">
*<s:complexType><s:sequence><s:element minOccurs="0" maxOccurs="1" name="login" type="s:string"/>
*<s:element minOccurs="0" maxOccurs="1" name="password" type="s:string"/>
*<s:element minOccurs="1" maxOccurs="1" name="IdExtSite" type="s:int"/>
*</s:sequence></s:complexType></s:element><s:element name="LogInResponse">
*<s:complexType><s:sequence><s:element minOccurs="1" maxOccurs="1" name="LogInResult" type="s:boolean"/>
*</s:sequence></s:complexType></s:element></s:schema></wsdl:types>
*<wsdl:message name="registerSoapIn"><wsdl:part name="parameters" element="tns:register"/>
*</wsdl:message><wsdl:message name="registerSoapOut"><wsdl:part name="parameters" element="tns:registerResponse"/>
*</wsdl:message><wsdl:message name="LogInSoapIn"><wsdl:part name="parameters" element="tns:LogIn"/>
*</wsdl:message><wsdl:message name="LogInSoapOut"><wsdl:part name="parameters" element="tns:LogInResponse"/>
*</wsdl:message><wsdl:portType name="wsRolISP_NESoap"><wsdl:operation name="register">
*<wsdl:input message="tns:registerSoapIn"/><wsdl:output message="tns:registerSoapOut"/>
*</wsdl:operation><wsdl:operation name="LogIn"><wsdl:input message="tns:LogInSoapIn"/>
*<wsdl:output message="tns:LogInSoapOut"/></wsdl:operation></wsdl:portType>
*<wsdl:binding name="wsRolISP_NESoap" type="tns:wsRolISP_NESoap">
*<soap:binding transport="http://schemas.xmlsoap.org/soap/http" style="document"/>
*<wsdl:operation name="register">
*<soap:operation soapAction="http://tempuri.org/register" style="document"/>
*<wsdl:input>
*<soap:body use="literal"/>
*</wsdl:input>
*<wsdl:output>
*<soap:body use="literal"/>
*</wsdl:output></wsdl:operation>
*<wsdl:operation name="LogIn">
*<soap:operation soapAction="http://tempuri.org/LogIn" style="document"/>
*<wsdl:input><soap:body use="literal"/></wsdl:input><wsdl:output><soap:body use="literal"/>
*</wsdl:output></wsdl:operation></wsdl:binding><wsdl:service name="wsRolISP_NE">
*<wsdl:port name="wsRolISP_NESoap" binding="tns:wsRolISP_NESoap">
*<soap:address location="http://test.secure.pandasoftware.com/services/rolispv2/wsRolISP_NE/wsRolISP_NE.asmx"/>
*</wsdl:port></wsdl:service></wsdl:definitions>
*
*/
package com.netPoint.applications.site.service.soap.panda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Faliherizo
 *
 */
/*@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "codeclient"
})
@XmlRootElement(name = "GetOrdersRequest")*/
public class LoginRequest {
	private String login;
	private String password;
	private int IdExtSite;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIdExtSite() {
		return IdExtSite;
	}
	public void setIdExtSite(int idExtSite) {
		IdExtSite = idExtSite;
	}
	

}
