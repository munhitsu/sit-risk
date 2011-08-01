<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xdt="http://www.w3.org/2003/11/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" version="2.0">
	<xsl:output encoding="ISO-8859-2" indent="yes" method="xml"/>

	<xsl:template match="*">
		<xsl:element name="{name()}">
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates select="child::node()"/>
		</xsl:element>
	</xsl:template>
	<xsl:template match="w3/w_suma_lekow">
		<xsl:copy-of select="."/>
		<xsl:element name="{concat(name(),'-div_2')}">
			<xsl:attribute name="mod">div</xsl:attribute>
			<xsl:value-of select=". div 2"/>
		</xsl:element>
		<xsl:element name="{concat(name(),'-mul')}">
			<xsl:attribute name="mod">mul</xsl:attribute>
			<xsl:choose>
				<xsl:when test="ancestor::pacjent/w2/w_suma_lekow = 0">
					<xsl:value-of select="0"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select=". div ancestor::pacjent/w2/w_suma_lekow"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:element>
		<xsl:element name="{concat(name(),'-diff')}">
			<xsl:attribute name="mod">diff</xsl:attribute>
			<xsl:value-of select=". - ancestor::pacjent/w2/w_suma_lekow"/>
		</xsl:element>
	</xsl:template>
	<xsl:template match="w4/w_suma_lekow">
		<xsl:copy-of select="."/>
		<xsl:element name="{concat(name(),'-div_4')}">
			<xsl:attribute name="mod">div</xsl:attribute>
			<xsl:value-of select=". div 4"/>
		</xsl:element>
		<xsl:element name="{concat(name(),'-mul')}">
			<xsl:attribute name="mod">mul</xsl:attribute>
			<xsl:choose>
				<xsl:when test="ancestor::pacjent/w3/w_suma_lekow = 0">
					<xsl:value-of select="0"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select=". div ancestor::pacjent/w3/w_suma_lekow"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:element>
		<xsl:element name="{concat(name(),'-diff')}">
			<xsl:attribute name="mod">diff</xsl:attribute>
			<xsl:value-of select=". - ancestor::pacjent/w3/w_suma_lekow"/>
		</xsl:element>
	</xsl:template>
	<xsl:template match="w5/w_suma_lekow">
		<xsl:copy-of select="."/>
		<xsl:element name="{concat(name(),'-div_8')}">
			<xsl:attribute name="mod">div</xsl:attribute>
			<xsl:value-of select=". div 8"/>
		</xsl:element>
		<xsl:element name="{concat(name(),'-mul')}">
			<xsl:attribute name="mod">mul</xsl:attribute>
			<xsl:choose>
				<xsl:when test="ancestor::pacjent/w4/w_suma_lekow = 0">
					<xsl:value-of select="0"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select=". div ancestor::pacjent/w4/w_suma_lekow"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:element>
		<xsl:element name="{concat(name(),'-diff')}">
			<xsl:attribute name="mod">diff</xsl:attribute>
			<xsl:value-of select=". - ancestor::pacjent/w4/w_suma_lekow"/>
		</xsl:element>
	</xsl:template>
	<xsl:template match="w6/w_suma_lekow">
		<xsl:copy-of select="."/>
		<xsl:element name="{concat(name(),'-div_16')}">
			<xsl:attribute name="mod">div</xsl:attribute>
			<xsl:value-of select=". div 16"/>
		</xsl:element>
		<xsl:element name="{concat(name(),'-mul')}">
			<xsl:attribute name="mod">mul</xsl:attribute>
			<xsl:choose>
				<xsl:when test="ancestor::pacjent/w5/w_suma_lekow = 0">
					<xsl:value-of select="0"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select=". div ancestor::pacjent/w5/w_suma_lekow"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:element>
		<xsl:element name="{concat(name(),'-diff')}">
			<xsl:attribute name="mod">diff</xsl:attribute>
			<xsl:value-of select=". - ancestor::pacjent/w5/w_suma_lekow"/>
		</xsl:element>
	</xsl:template>
	<xsl:template match="w7/w_suma_lekow">
		<xsl:copy-of select="."/>
		<xsl:element name="{concat(name(),'-div_32')}">
			<xsl:attribute name="mod">div</xsl:attribute>
			<xsl:value-of select=". div 32"/>
		</xsl:element>
		<xsl:element name="{concat(name(),'-mul')}">
			<xsl:attribute name="mod">mul</xsl:attribute>
			<xsl:choose>
				<xsl:when test="ancestor::pacjent/w6/w_suma_lekow = 0">
					<xsl:value-of select="0"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select=". div ancestor::pacjent/w6/w_suma_lekow"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:element>
		<xsl:element name="{concat(name(),'-diff')}">
			<xsl:attribute name="mod">diff</xsl:attribute>
			<xsl:value-of select=". - ancestor::pacjent/w6/w_suma_lekow"/>
		</xsl:element>
	</xsl:template>
	<xsl:template match="wk/w_suma_lekow">
		<xsl:copy-of select="."/>
		<xsl:element name="{concat(name(),'-div_32')}">
			<xsl:attribute name="mod">div</xsl:attribute>
			<xsl:value-of select=". div 32"/>
		</xsl:element>
		<xsl:element name="{concat(name(),'-mul')}">
			<xsl:attribute name="mod">mul</xsl:attribute>
			<xsl:choose>
				<xsl:when test="ancestor::pacjent/w6/w_suma_lekow = 0">
					<xsl:value-of select="0"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select=". div ancestor::pacjent/w6/w_suma_lekow"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:element>
		<xsl:element name="{concat(name(),'-diff')}">
			<xsl:attribute name="mod">diff</xsl:attribute>
			<xsl:value-of select=". - ancestor::pacjent/w6/w_suma_lekow"/>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>

