<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xdt="http://www.w3.org/2003/11/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" version="2.0">
	<xsl:output encoding="ISO-8859-2" indent="yes" method="xml"/>
	<xsl:param name="usrednioneFile"/>

	<xsl:template match="pacjenci">
		<xsl:message>usrednioneFile = <xsl:value-of select="$usrednioneFile"/></xsl:message>
		<xsl:element name="pacjenci">
			<xsl:apply-templates select="child::node()">
				<xsl:with-param name="daneUsrednione"><xsl:copy-of select="document($usrednioneFile)"/></xsl:with-param>
			</xsl:apply-templates>
		</xsl:element>
	</xsl:template>

	<xsl:template match="pacjent">
		<xsl:param name="daneUsrednione"/>
		<xsl:element name="pacjent">
			<xsl:copy-of select="@*"/>
			<xsl:variable name="grupaPacjentow" select="wywiad/grupa_pacjentow"/><!--cache-->
			<xsl:for-each select="*">
				<xsl:variable name="grupa" select="name(.)"/>
				<xsl:element name="{$grupa}">
					<xsl:copy-of select="@*"/>
					<xsl:for-each select="*">
						<xsl:variable name="atrybut" select="name(.)"/>
						<xsl:element name="{$atrybut}">
							<xsl:copy-of select="@*"/>
							<xsl:choose>
								<xsl:when test=". castable as xs:decimal">
									<xsl:attribute name="wiarygodnosc">oryginal</xsl:attribute>
									<xsl:value-of select="."/>
								</xsl:when>
								<xsl:otherwise>
									<xsl:attribute name="wiarygodnosc">srednia</xsl:attribute>
									<xsl:value-of select="$daneUsrednione//pacjenci/pacjent[wywiad/grupa_pacjentow=$grupaPacjentow]/*[name(.) = $grupa]/*[name(.) = $atrybut]"/>
								</xsl:otherwise>
							</xsl:choose>
						</xsl:element>
					</xsl:for-each>
				</xsl:element>
			</xsl:for-each>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
