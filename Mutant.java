import java.util.ArrayList;

class Mutant {
    int dna_len;
    int counter;

    Mutant(){
    }

    public static void main(String[] args){
        String[] dna = {"ATGCGM", "CAGTMC", "TTAMGT", "AGMAGG", "CCCCTA", "TCACTG", "ADF"};
        Mutant mutant = new Mutant();
        mutant.isMutant(dna);
    }

    private boolean isMutant(String[] dna){
        ArrayList<char[]> array = new ArrayList<>();

        int len;
        int max_width = 0;
        int max_height;
        counter = 0;
        dna_len = 4;
        max_height = dna.length;
        char[] vertical = new char[max_height];

        //first convert to char[] and get max_length
        for (String i: dna){
            array.add(i.toCharArray());

            len = i.length();
            if(max_width < len){
                max_width = len;
            }
        }


        //fill the matrix
        char[][] matrix = new char[max_height][max_width];
        for(int i=0; i<max_height; i++){
            char[] tmp = array.get(i);

            //check for horizontal cases
            check_horizontal(tmp);
            if(counter > 1){
                System.out.println(counter);
                return true;
            }

            int tmp_len = tmp.length;
            for(int j=0; j<max_width; j++){
                //ensure index, and set empty in other case
                char elem = (j < tmp_len) ? tmp[j] : 0;

                matrix[i][j] = elem;
            }
        }

        //iterate vertical and check
        for(int j=0; j<max_width; j++){
            char[] tmp = new char[max_height];
            for(int i=0; i<max_height; i++){
                tmp[i] = matrix[i][j];
            }

            check_horizontal(tmp);

            if(counter > 1){
                System.out.println(counter);
                return true;
            }
        }

       
        int rest;
        int len_items;
        
        //iterate in diagonal top-left to bottom-right (\)
        for(int i=0; i<=max_height - dna_len; i++){
            rest = max_height - i;
            len_items = (rest < max_width) ? rest : max_width;
            char[] tmp = new char[len_items];

            for(int j=0; j<len_items; j++){
                tmp[j] = matrix[j+i][j];
            }

            check_horizontal(tmp);
        }

        //iterate after the center
        for(int i=1; i<=max_width - dna_len; i++){
            rest = max_width - i;
            len_items = (rest < max_height) ? rest : max_height;
            char[] tmp = new char[len_items];

            for(int j=0; j<len_items; j++){
                tmp[j] = matrix[j][j+i];
            }

            check_horizontal(tmp);
            if(counter > 1){
                System.out.println(counter);
                return true;
            }
        }


        //iterate in diagonal bottom-left to top-right (/)
        for(int i=max_height-1; i >= dna_len-1; i--){
            rest = i+1;
            len_items = (rest < max_width) ? rest : max_width;
            char[] tmp = new char[len_items];


            for(int j=0; j<len_items; j++){
                tmp[j] = matrix[i-j][j];
            }

            check_horizontal(tmp);
            if(counter > 1){
                System.out.println(counter);
                return true;
            }
        }
        

        //iterate after the center
        for(int i=1; i<=max_width - dna_len; i++){
            rest = max_width - i;
            len_items = (rest < max_height) ? rest : max_height;
            char[] tmp = new char[len_items];

            for(int j=0; j<len_items; j++){
                tmp[j] = matrix[max_width-1-j][j+i];
            }

            check_horizontal(tmp);
            if(counter > 1){
                System.out.println(counter);
                return true;
            }
        }

        System.out.println(counter);
        return false;
    }

    private void check_horizontal(char[] item){
        int l = item.length;
        char flag;

        if(l < dna_len){
            return;
        }

        for(int i=0; i<l-dna_len+1; i++){
            flag = item[i];

            //check first and last elements inside the window(dna_len)
            for(int j=i+dna_len-1; j>i; j--){
                if(flag != item[j]){
                    break;
                }

                if(j > i+1){
                    continue;
                }

                counter++;
                
                if(counter > 1){
                    return;
                }

                //move the window to the next chunk
                i += dna_len -1;
            }
        }
    }
}

