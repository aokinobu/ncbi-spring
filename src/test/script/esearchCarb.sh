#!/usr/bin/perl -w

use LWP::Simple;

sub query_year {
#  print "$_[0]\n";
  my $query = $_[0];
  my $year = $_[1];

  my $utils = "https://www.ncbi.nlm.nih.gov/entrez/eutils";

  #my $db     = "PMC";
  my $db     = "Pubmed";
  #print "db: $db\n";
  $query  = "$query AND $year/1/1:$year/12/31[Publication Date]";
  print "$query\n";
  my $esearch = "$utils/esearch.fcgi?" .
                "db=$db&retmax=1&usehistory=y&term=";
  my $esearch_result = get($esearch . $query);

#  print "\nESEARCH RESULT: $esearch_result\n";

  $esearch_result =~ m|<Count>(\d+)</Count>.*<QueryKey>(\d+)</QueryKey>.*<WebEnv>(\S+)</WebEnv>|s;

  my $Count    = $1;
  my $QueryKey = $2;
  my $WebEnv   = $3;
  print "Count = $Count\n";
}

my $query = "Carbohydrate*";

query_year($query, "2010");
query_year($query, "2011");
query_year($query, "2012");
query_year($query, "2013");
query_year($query, "2014");
query_year($query, "2015");
query_year($query, "2016");