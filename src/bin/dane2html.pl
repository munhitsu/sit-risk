#!/usr/bin/perl -w

my @row;

print "<?xml version=\"1.0\" encoding=\"ISO-8859-2\"?>\n<html>\n<body>\n<table>\n";
while (<>) {
	chomp;
	@row = split(" ");
	print "<tr>";
	foreach (@row) {
		if ($_ == -999) {
			print "<td></td>";
		} else {
			print "<td>".$_."</td>";
		}
	}
	print "</tr>\n";
}

print "</table>\n</body>\n</html>\n";

