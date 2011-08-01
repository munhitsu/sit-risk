<?xml version="1.0" encoding="ISO-8859-2"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
		xmlns:xs="http://www.w3.org/2001/XMLSchema"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns:fn="http://www.w3.org/2003/11/xpath-functions"
		xmlns:xdt="http://www.w3.org/2003/11/xpath-datatypes">
	
	<xsl:output method="xhtml" encoding="ISO-8859-2" indent="yes"/>
	
	<xsl:param name="rowsXPath" select="//pacjenci/pacjent[(position() &lt;= 32)]"/>
	<xsl:param name="learningXPath" select="*[self::wywiad or self::w1 or self::w2]/*"/>
	<xsl:param name="validatingXPath" select="computed/stopien_pogorszenia_max"/>
	
	<xsl:param name="polaFile" select="data.conf/pola-edited-with-ranges.xml"/>
	<xsl:variable name="polaDoc" select="document($polaFile)"/>
	<xsl:variable name="pola" select="$polaDoc//pola/pole"/>
	
	<xsl:template match="pacjenci">
		<html>
			<body>
				<table cellpadding="1" cellspacing="0" border="1">
<!--					<xsl:for-each select="$polaDoc//pola/pole">
						<xsl:message>
							<xsl:value-of select="@type"/>
						</xsl:message>
					</xsl:for-each>-->
					<xsl:for-each select="//pacjenci/pacjent[(position() &lt;= 32)]">
						<xsl:if test="position() = 1">
							<thead>
								<tr>
									<td>kategoria_elementu</td>
									<xsl:for-each select="*[self::wywiad or self::w1 or self::w2]/*">
										<td>
											<xsl:variable name="codename" select="name()"/>
											<xsl:value-of select="$polaDoc//pola/pole[@codename=$codename]/@type"/>-<xsl:value-of select="name(parent::*)"/>-<xsl:value-of select="name()"/>
										</td>
									</xsl:for-each>
									<xsl:for-each select="computed/stopien_pogorszenia_max">
										<td>
											<xsl:value-of select="name(parent::*)"/>-<xsl:value-of select="name()"/>
										</td>
									</xsl:for-each>
									<xsl:message>head</xsl:message>
								</tr>
							</thead>
						</xsl:if>
						<tr>
							<xsl:choose>
								<xsl:when test="(position() mod 5) = 1">
									<td>walidacja</td>
								</xsl:when>
								<xsl:when test="(position() mod 5) = 2">
									<td>test</td>
								</xsl:when>
								<xsl:otherwise>
									<td>nauka</td>
								</xsl:otherwise>
							</xsl:choose>
							<xsl:for-each select="*[self::wywiad or self::w1 or self::w2]/*">
								<xsl:choose>
									<xsl:when test="fn:string-length(.) != 0">
										<td>
											<xsl:variable name="input" select="."/>
											<xsl:value-of select="fn:replace($input,'\.',',')"/>
										</td>
									</xsl:when>
									<xsl:otherwise>
										<td bgcolor="#FF0000"/>
									</xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
							<xsl:for-each select="computed/*">
								<xsl:choose>
									<xsl:when test="fn:string-length(.) != 0">
										<td>
											<xsl:variable name="input" select="."/>
											<xsl:value-of select="fn:replace($input,'\.',',')"/>
										</td>
									</xsl:when>
									<xsl:otherwise>
										<td bgcolor="#FF0000"/>
									</xsl:otherwise>
								</xsl:choose>
							</xsl:for-each>
							<xsl:message>row</xsl:message>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>

