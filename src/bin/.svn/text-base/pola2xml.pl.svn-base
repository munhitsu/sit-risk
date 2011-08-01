#!/usr/bin/perl -w
use strict;

sub getCodeName {
	my $line = $_[0];
	$line =~ tr/A-Z /a-z_/;
	$line =~ tr/.\(\)//d;
	$line =~ s/%/procent/;
	$line =~ s/\//_lub_/;
	$line =~ s/\>/_wieksze_/;
	$line =~ s/\=/_rowne_/;
	$line =~ s/\</_mniejsze_/;
	$line =~ s/__/_/g;
	$line =~ tr/ÊÓ¡¦£¯¬ÆÑêó±¶³¿¼æñ/EOASLZXCNeoaslzxcn/;
	return $line;
}

my $codeName;
my $fullName;
my $type;
my $columns;
my %pola;
my $removed;

print "<pola>\n";
my $id = 1;
my $col = 1;
while (<>) {
	chomp($_);
	$fullName = $_;
	if ($fullName =~ /^\#(.*)$/) {
		$removed = 1;
		$fullName = $1;
	} else {
		$removed = 0;
	}
	$codeName = getCodeName($_);
	
	#rok wynikow / numer testow
	if ($codeName =~ m/^((w(\d|k))|(testy_\d))_(.*)$/) {
		$type = $1;
		$codeName = $5;
		if ($fullName =~ m/^((w(\d|k))|(testy \d)) (.*)$/i) {
			$fullName = $5;
			if ($type =~ m/^w/i) {
				$fullName = "w ".$fullName;
				$codeName = "w_".$codeName;
			} elsif ($type =~ m/^testy/i) {
				$fullName = "testy ".$fullName;
				$codeName = "testy_".$codeName;
			}
		}
	} else {
		if ($id < 50) {
			$type = "wywiad";
		} else {
			$type = "other";
		}
	}

	if (!$removed) {
		if (defined($pola{$codeName})) {
#			$pola{$codeName}{"columns"} .= ", $col";
			$pola{$codeName}{$type} = "$col";
		} else {
#			$columns = "$col";
			$pola{$codeName} = {
				codename => $codeName,
#				fullname => $fullName,
				id => "$id",
#				columns => $columns
			};
			$pola{$codeName}{$type} = "$col";
			$id++;
		}
	}
	$col++;
}
my $fieldName;
foreach $codeName (sort { $pola{$a}{id} <=> $pola{$b}{"id"}} keys %pola) {
	print "  <pole";
	foreach $fieldName (sort keys %{$pola{$codeName}}) {
		print " ".$fieldName."=\"".$pola{$codeName}{$fieldName}."\"";
	}
	print " />\n";
}
print "</pola>\n";

