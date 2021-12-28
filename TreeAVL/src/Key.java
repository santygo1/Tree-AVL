public class Key {

    private final char graduate;
    private final int group;

    Key(char graduate, int group){
        this.graduate = graduate;
        this.group = group;
    }

    /**
     * Функция сравнения двух ключей
     * @param key - предложенный ключ
     * @return 1 - если этот ключ больше предложенного;
     *         0 - если этот ключ равна предложенного;
     *         -1 - если этот ключ меньше предложенного;
     */
    public short compareTo(Key key){
        if(this.graduate < key.graduate) return -1;
        else if(this.graduate == key.graduate){
            if(this.group < key.group) return  -1;
            else if(this.group == key.group) return 0;
            return 1;
        }
        return 1;
    }

    /**
     * Переопределенный метод Object.toString() для вывода значения ключа
     * @return строковое значение ключа
     */
    @Override
    public String toString() {
        return graduate + "|" + group;
    }
}
