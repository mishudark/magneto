import java.util.ArrayList;

class Mutant {
    int dna_len;
    int counter;

    Mutant(){
    }

    public static void main(String[] args){
      String[] dna = {"ATGCGA", "CAGTGC", "TTATGA", "AGAAGG", "CCCCTA", "TCACTG"};
      Mutant mutant = new Mutant();
      mutant.isMutant(dna);
    }

    private boolean isMutant(String[] dna){
        ArrayList<char[]> array = new ArrayList<>();
        ArrayList<char[]> helper = new ArrayList<>();

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
            System.out.println(counter);
            if(counter > 1){
              return true;
            }

            for(int j=0; j<max_width; j++){
                matrix[i][j] = tmp[j];
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
            //check first and last elements, iterate 
            for(int j=i+dna_len-1; j>i; j--){
                if(flag != item[j]){
                    break;
                }else if(j>i+1){
                  continue;
                }

                counter++;

                if(counter > 1){
                  return;
                }

                i += dna_len -1;
            }
        }
    }

}

