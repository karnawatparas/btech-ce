BEGIN {
	# Begin block is executed first -
	# Before the operations performed on each line
	# The following line will be printed first.
	print "AWK program to calculate grades"
}
# The following block is executed for each line
{
	total = 0
	grade = ""

	# NF : Number of fields
	# NR : Number of records
	# $ is used to access the variable in specified column number

	# the following loop calculates total for all subjects specified after name
	for(i = 2; i <= NF; i++) {
		total = total + $i
	}

	#Calculating average/percentage
	total /= (NF - 1)

	if (total > 90) {
		grade = "O"
	} else if (total > 80) {
		grade = "A+"
	} else if (total > 70) {
		grade = "A"
	} else if (total > 60) {
		grade = "B+"
	} else if (total > 50) {
		grade = "B"
	} else if (total > 40) {
		grade = "C"
	} else if (total == 40) {
		grade = "P"
	} else {
		grade = "F"
	}

	# $0 is used to point the whole line
	print $1, " : Percetange - ", total, "%, Grade - ", grade
}

END {
	# The END block is executed at the last after running for each line
	print "Completed calculating grades"
}
