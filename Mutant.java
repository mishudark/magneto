import java.util.ArrayList;

class Mutant {
    int dna_len;
    int counter;

    Mutant(){
    }

    public static void main(String[] args){
        String[] dna = {"000M0", "00M000", "0M0000", "M00000", "000000", "000000"};
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

        //iterate in diagonal top-left to bottom-right (\)
        char[] tmp = new char[dna_len];
        for(int i=0; i <= max_width - dna_len; i++){
            for(int j=0; j <= max_height - dna_len; j++){
                //create diagonal array
                for(int k=0; k<dna_len; k++){
                    tmp[k] = matrix[i+k][j+k];
                }
                check_horizontal(tmp);
            } 
        }

        //iterate in diagonal bottom-left to top-right (/)
        for(int i=0; i <= max_width - dna_len; i++){
            for(int j=max_height-1; j >= dna_len-1; j--){
                //create diagonal array

                for(int k=0; k<dna_len; k++){
                    tmp[k] = matrix[i+k][j-k];
                }
                check_horizontal(tmp);
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

            //check first and last elements, iterate 
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

