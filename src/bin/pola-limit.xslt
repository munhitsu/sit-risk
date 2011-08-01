<?xml version="1.0" encoding="ISO-8859-2"?>
<!-- the style is used to remove unwanted elements:<pole> -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:fn="http://www.w3.org/2003/11/xpath-functions"
	xmlns:xdt="http://www.w3.org/2003/11/xpath-datatypes">
	
	
	<xsl:param name="priorityUsed" select="2"/>

	<xsl:template match="/*">
		<xsl:message><xsl:text disable-output-escaping="yes">selecting nodes with priority &lt;= </xsl:text><xsl:value-of select="$priorityUsed"/></xsl:message>
		<xsl:element name="{name()}">
			<xsl:apply-templates select="*">
				<xsl:with-param name="root" select="name()"/>
				<xsl:sort data-type="number" select="@id"/>
			</xsl:apply-templates>
		</xsl:element>
	</xsl:template>
	
	<xsl:template match="/*/*">
		<xsl:param name="root" select="name(/)"/>
		<xsl:choose>
			<xsl:when test="xs:integer(@priority) le xs:integer($priorityUsed)">
				<xsl:copy-of select="."/>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>

