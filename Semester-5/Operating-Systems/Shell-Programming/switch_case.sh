while [ 1 ]; do
    echo "1. Substring Occurence"
    echo "2. Palindrome"
    echo "3. Exit"

    read -p "Enter your choice >> " choice

    case $choice in
    1)
        read -p "    Enter string : " str
        read -p "    Enter substring: " substring

        strlen=${#str}
        sublen=${#substring}
        diff=$(expr $strlen - $sublen)

        occurence=0

        for ((i = 0; i <= $diff; i++)); do
            j=0
            flag=0
            for ((j = 0; j < $sublen; j++)); do
                var=$(expr $i + $j)
                c1=${str:$var:1}
                c2=${str:$j:1}
                if [[ $c1 != $c2 ]]; then
                    flag=1
                    break
                fi
            done
            if [ $flag -eq 0 ]; then
                echo "    Index: $i"
                occurence=`expr $occurence + 1`
            fi
        done
        echo "    Occurences: $occurence"
        ;;
    2)
        read -p "    Enter string : " str
        len=${#str}
        flag=1
        half=`expr $len / 2`
        for((i=0;i<=$half;i++)); do
            c1=${str:$i:1}
            ((ind=$len - $i - 1))
            c2=${str:$ind:1}
            if [[ "$c1" != "$c2" ]]; then
                echo "    Not palindrome"
                flag=0
                break
            fi
        done
        if [[ $flag == 1 ]]; then
            echo "    Palindrome"
        fi
        ;;
    3)
        break
        ;;
    esac
done

echo "Division: "
num1=50
num2=2
res=`expr $num1 / $num2`
echo -ne "$res \t"
num1=49
res=`expr $num1 / $num2`
echo -ne "$res \n"
printf %f\\n $(($num1 / $num2))