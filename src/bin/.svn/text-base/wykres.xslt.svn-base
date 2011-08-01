<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xdt="http://www.w3.org/2003/11/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" version="2.0">
	<xsl:output encoding="UTF-8" indent="yes" method="xml"/>
	<xsl:param name="box">5</xsl:param>
	<xsl:param name="bigBoxX">50</xsl:param>
	<xsl:param name="bigBoxY">200</xsl:param>
	<xsl:param name="bigBoxHead">40</xsl:param>
	<xsl:param name="textLine">15</xsl:param>
	<xsl:template match="pacjenci">
		<xsl:element name="svg">
			<xsl:attribute name="xml:space" >preserve</xsl:attribute>
<!--			<xsl:apply-templates select="child::node()"/>-->
			<xsl:variable name="pacjenci"><xsl:copy-of select="/"/></xsl:variable>
			
			<xsl:for-each select="('w_suma_objawow','w_suma_lekow','w_swiszczacy_oddech','w_kaszel','w_dusznosc','w_swiad_spojowek','w_lzawienie_oczu','w_wyciek_z_nosa','w_zatkanie_nosa','w_napady_kichania','w_wysypki_skorne','w_eozynofilia')">
				<xsl:variable name="pos" select="position()-1"/>
				<xsl:variable name="atrybut" select="."/>
				<xsl:call-template name="chart">
					<xsl:with-param name="atrybut"><xsl:value-of select="$atrybut"/></xsl:with-param>
					<xsl:with-param name="y"><xsl:value-of select="$pos*$bigBoxY"/></xsl:with-param>
					<xsl:with-param name="pacjenci"><xsl:copy-of select="$pacjenci"/></xsl:with-param>
					<xsl:with-param name="kroki" select="('w1','w2','w3','w4','w5','w6','w7')"/>
				</xsl:call-template>
			</xsl:for-each>

			<xsl:for-each select="('testy_trawy_i','testy_trawy_ii')">
				<xsl:variable name="pos" select="position()-1+12"/>
				<xsl:variable name="atrybut" select="."/>
				<xsl:call-template name="chart">
					<xsl:with-param name="atrybut"><xsl:value-of select="$atrybut"/></xsl:with-param>
					<xsl:with-param name="y"><xsl:value-of select="$pos*$bigBoxY"/></xsl:with-param>
					<xsl:with-param name="pacjenci"><xsl:copy-of select="$pacjenci"/></xsl:with-param>
					<xsl:with-param name="kroki" select="('testy_1','testy_2','testy_3')"/>
				</xsl:call-template>
			</xsl:for-each>
			
		</xsl:element>
	</xsl:template>
	
	<xsl:template name="chart">
		<xsl:param name="atrybut">w_suma_objawow</xsl:param>
		<xsl:param name="y">0</xsl:param>
		<xsl:param name="pacjenci"/>
		<xsl:param name="kroki"/>
		
		<xsl:message>drawing:<xsl:value-of select="$atrybut"/>(<xsl:value-of select="$y"/>)</xsl:message>
		<xsl:variable name="values" select="$pacjenci//pacjent/*/*[name()=$atrybut]"/>
		<xsl:variable name="maxY" select="max(for $i in $values return (if ($i castable as xs:double ) then xs:double($i) else 0))"/>
		<xsl:message>maximum value:<xsl:value-of select="$maxY"/></xsl:message>
		
		<xsl:element name="text">
			<xsl:attribute name="style">fill:black;stroke:none;font-family:helvetica;font-style:normal;font-weight:normal;font-size:12px;fill-opacity:1;stroke-opacity:1;stroke-width:1pt;stroke-linejoin:miter;stroke-linecap:butt;</xsl:attribute>
			<xsl:attribute name="x"><xsl:value-of select="0"/></xsl:attribute>
			<xsl:attribute name="y"><xsl:value-of select="$bigBoxHead -  $textLine + $y"/></xsl:attribute>
			<xsl:element name="tspan">
				<xsl:value-of select="$atrybut"/>
			</xsl:element>
		</xsl:element>
		
		<xsl:for-each select="$pacjenci//pacjent">
			<xsl:variable name="pacjent"><xsl:copy-of select="."/></xsl:variable>
			<xsl:variable name="pacjentNr" select="position()"/>
		<xsl:element name="text">
			<xsl:attribute name="style">fill:black;stroke:none;font-family:helvetica;font-style:normal;font-weight:normal;font-size:12px;fill-opacity:1;stroke-opacity:1;stroke-width:1pt;stroke-linejoin:miter;stroke-linecap:butt;</xsl:attribute>
			<xsl:attribute name="x"><xsl:value-of select="($pacjentNr - 1) * $bigBoxX"/></xsl:attribute>
			<xsl:attribute name="y"><xsl:value-of select="$bigBoxHead  + $y"/></xsl:attribute>
			<xsl:element name="tspan">
				<xsl:value-of select="$pacjentNr"/>
			</xsl:element>
		</xsl:element>

			<xsl:for-each select="$kroki">
				<xsl:variable name="pos" select="position()-1"/>
				<xsl:variable name="wx" select="."/>
				<xsl:variable name="empty" select="if (empty($pacjent//*[name() = $wx]/*[name() = $atrybut])) then 1 else 0"/>
				<xsl:variable name="val" select="if ($empty = 1) then -0.5 else $pacjent//*[name() = $wx]/*[name() = $atrybut]"/>
				<xsl:variable name="wiarygodnosc" select="$pacjent//*[name() = $wx]/*[name() = $atrybut]/@wiarygodnosc"/>
<!--				<xsl:message><xsl:value-of select="$wx"/>:<xsl:value-of select="$pos"/>:<xsl:value-of select="$val"/></xsl:message>-->
<!--				<xsl:message><xsl:value-of select="$wiarygodnosc"/></xsl:message>-->
				<xsl:element name="rect">
					<xsl:choose>
						<xsl:when test="$empty = 1">
							<xsl:attribute name="style">font-size:12;fill:#AAAAAA;fill-rule:evenodd;stroke-width:0;</xsl:attribute>
							<xsl:attribute name="height"><xsl:value-of select="$box * 0.5"/></xsl:attribute>
						</xsl:when>
						<xsl:when test="$wiarygodnosc = 'oryginal'">
							<xsl:attribute name="style">font-size:12;fill:#0000FF;fill-rule:evenodd;stroke-width:0;</xsl:attribute>
							<xsl:attribute name="height"><xsl:value-of select="($val + 1) * $box"/></xsl:attribute>
						</xsl:when>
						<xsl:when test="$wiarygodnosc = 'spline'">
							<xsl:attribute name="style">font-size:12;fill:#FF0000;fill-rule:evenodd;stroke-width:0;</xsl:attribute>
							<xsl:attribute name="height"><xsl:value-of select="($val + 1) * $box"/></xsl:attribute>
						</xsl:when>
						<xsl:otherwise>
							<xsl:attribute name="style">font-size:12;fill:#00FF00;fill-rule:evenodd;stroke-width:0;</xsl:attribute>
							<xsl:attribute name="height"><xsl:value-of select="($val + 1) * $box"/></xsl:attribute>
						</xsl:otherwise>
					</xsl:choose>
					<xsl:attribute name="width"><xsl:value-of select="$box"/></xsl:attribute>

					<xsl:attribute name="x"><xsl:value-of select="$pos * $box + ($pacjentNr - 1) * $bigBoxX"/></xsl:attribute>
					<xsl:attribute name="y"><xsl:value-of select="$bigBoxY - (($val + 1) * $box) + $y"/></xsl:attribute>
				</xsl:element>
			</xsl:for-each>
		</xsl:for-each>
	</xsl:template>
	
</xsl:stylesheet>

