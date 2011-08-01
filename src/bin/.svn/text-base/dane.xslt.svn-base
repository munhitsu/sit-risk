<?xml version="1.0" encoding="ISO-8859-2"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:fn="http://www.w3.org/2003/11/xpath-functions"
	xmlns:xdt="http://www.w3.org/2003/11/xpath-datatypes">
	
	<xsl:output method="xml" encoding="ISO-8859-2" indent="yes"/>
	<xsl:param name="polaFile" select="nazwy.xml"/>
	<xsl:param name="grupyFile" select="grupy.xml"/>
	<xsl:param name="priorityUsed" select="1"/>
	
	<xsl:template match="table">
		<xsl:message>pola:<xsl:value-of select="$polaFile"/></xsl:message>
		<xsl:message>grupy:<xsl:value-of select="$grupyFile"/></xsl:message>
		<xsl:message>priority:<xsl:value-of select="$priorityUsed"/></xsl:message>
		<xsl:element name="pacjenci">
			<xsl:variable name="pola" select="document($polaFile)"/>
			<xsl:variable name="grupy" select="document($grupyFile)"/>
			<xsl:for-each select="tr">
				<xsl:variable name="tr" select="."/>
				<xsl:element name="pacjent">
					<xsl:message>pacjent: <xsl:value-of select="position()"/></xsl:message>
					<xsl:for-each select="$grupy//grupy/grupa">
						<xsl:variable name="groupName" select="@name"/>
						<xsl:element name="{$groupName}">
							<xsl:for-each select="$pola//pola/pole[attribute::*[name()=$groupName]]">
								<xsl:variable name="wkColumn" select="attribute::*[name()=$groupName]"/>
								<xsl:variable name="codeName" select="attribute::codename"/>
								<xsl:if test="xs:integer(attribute::priority) le xs:integer($priorityUsed)">
<!--								<xsl:message><xsl:value-of select="$wkColumn"/>:<xsl:value-of select="$codeName"/>:<xsl:value-of select="$tr/td[position()=$wkColumn]"/></xsl:message>-->
<!--								<xsl:if test="$tr/td[position()=$wkColumn] != &apos;&apos;">-->
									<xsl:element name="{$codeName}">
										<xsl:attribute name="priority"><xsl:value-of select="@priority"/></xsl:attribute>
										<xsl:value-of select="$tr/td[position()=$wkColumn]"/>
									</xsl:element>
								</xsl:if>
							</xsl:for-each>
						</xsl:element>
					</xsl:for-each>
				</xsl:element>
			</xsl:for-each>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>

