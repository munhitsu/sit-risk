<?xml version="1.0" encoding="ISO-8859-2"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
		xmlns:xs="http://www.w3.org/2001/XMLSchema"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns:fn="http://www.w3.org/2003/11/xpath-functions"
		xmlns:xdt="http://www.w3.org/2003/11/xpath-datatypes">
	
	<xsl:param name="grupyFile" select="grupy.xml"/>
	<xsl:variable name="grupyDoc" select="document($grupyFile)"/>
	<xsl:variable name="grupy" select="$grupyDoc//grupy/grupa/@name"/>
	<xsl:output encoding="ISO-8859-2" indent="yes" method="xhtml"/>
	
	<xsl:template match="/">
		<html>
			<body>
				<xsl:apply-templates/>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="pola">
		<table cellpadding="1" cellspacing="0">
			<thead>
				<tr>
					<td><b>nazwa</b></td>
					<td><b>priorytet</b></td>
					<xsl:for-each select="$grupy">
						<td><b><xsl:value-of select="."/></b></td>
					</xsl:for-each>
					<td align="right"><b>type</b></td>
					<td align="right"><b>min</b></td>
					<td align="right"><b>max</b></td>
				</tr>
			</thead>
			<xsl:apply-templates select="node()">
				<xsl:sort data-type="number" select="@priority"/>
				<xsl:sort select="@id"/>
			</xsl:apply-templates>
		</table>
	</xsl:template>
	<xsl:template match="pole">
		<xsl:variable name="pole" select="."/>
		<tr>
			<td><xsl:value-of select="@codename"/></td>
			<td><xsl:value-of select="@priority"/></td>
			<xsl:for-each select="$grupy">
				<xsl:variable name="grupa" select="."/>
				<xsl:choose>
					<xsl:when test="$pole/attribute::*[name()=$grupa]">
						<td bgcolor="#FFFFDD">
							<xsl:text>T</xsl:text>
						</td>
					</xsl:when>
					<xsl:otherwise>
						<td bgcolor="#FFDDDD">
							<xsl:text>N</xsl:text>
						</td>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:for-each>
			<td align="right"><xsl:value-of select="@type"/></td>
			<td align="right"><xsl:value-of select="@min"/></td>
			<td align="right"><xsl:value-of select="@max"/></td>
		</tr>
	</xsl:template>
</xsl:stylesheet>



