const app = Vue.createApp({
    data() {
        return {
            previous: "",
            current: "",
            operator: "",
            show: "",
            log: [],
            product: 'Kalkulator',
            brand: 'Team 17',
            description: 'Mogen, Schiøll-Johansen, Steira',
            decimal: false
        }
    },
    methods: {
        setCurrent(value){
            selectedNumber = value.toString();
            this.current += selectedNumber;
            this.show = this.current; 
        },
        operate(value){
            selectedOperator = value;
            
            if (this.current && this.previous)
                this.current = this.calculate();
            
            if (selectedOperator === '=') this.operator = "";
            else this.operator = selectedOperator;
            this.previous = this.current;
            this.show = this.current;
            this.current = ""; 

            if (decimal) decimal = false; 
        },
        calculate(){
            if (this.current === "")
                return;

            previous = parseInt(this.previous);
            current = parseInt(this.current);

            sum = "";
            if (this.operator == '+') sum = previous += current;
            if (this.operator == '-') sum = previous -= current;
            if (this.operator == '*') sum = previous *= current;
            if (this.operator == '/') sum = previous /= current; 
            //if (this.operator == '=') sum = previous; 
            
            // TODO: add to log
            return sum;
        },
        addToLog(variantImage){
            this.image = variantImage; 
        },

        clear(){
            this.current = "";
            this.previous = "";
            this.show = ""; 
        }
    },
    computed: {
        title() {
            return this.brand + " " + this.product;
        }
    }
})