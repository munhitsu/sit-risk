<?xml version="1.0" encoding="ISO-8859-2"?>
<!-- the style is used to merge input with file defined with addFile 
	optimized for list of nodes with unique attribute -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:fn="http://www.w3.org/2003/11/xpath-functions"
	xmlns:xdt="http://www.w3.org/2003/11/xpath-datatypes">
	
	<xsl:param name="addFile" select="nazwy-edited.xml"/>
	<xsl:variable name="add" select="document($addFile)"/>

	<xsl:template match="/*">
		<xsl:message>merging input with: <xsl:value-of select="$addFile"/></xsl:message>
		<xsl:element name="{name()}">
			<xsl:apply-templates select="*">
				<xsl:with-param name="root" select="name()"/>
				<xsl:sort data-type="number" select="@id"/>
			</xsl:apply-templates>
		</xsl:element>
	</xsl:template>
	
	<xsl:template match="/*/*">
		<xsl:param name="root" select="name(/)"/>
		<xsl:variable name="element" select="name()"/>
		<xsl:element name="{$element}"> 
			<xsl:copy-of select="attribute::*"/>
			<xsl:variable name="id" select="@id"/>
			<xsl:copy-of select="$add//*[name() = $root]/*[name() = $element][attribute::id = $id]/attribute::*"/>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>

