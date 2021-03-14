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

            this.operator = selectedOperator;

            if (this.current === '') return; 

            this.previous = this.current;
            this.show = this.current;
            this.current = ""; 

            if (this.decimal) this.decimal = false; 
            
        },
        equals(){
            this.current = this.calculate()
            this.previous = this.current;
            this.show = this.current;
            this.current = ""; 
        },
        calculate(){
            if (this.current === "")
                return;
            if (this.operator === '=')
                return; 

            previous = parseFloat(this.previous);
            current = parseFloat(this.current);

            sum = "";
            if (this.operator == '+') sum = previous += current;
            if (this.operator == '-') sum = previous -= current;
            if (this.operator == '*') sum = previous *= current;
            if (this.operator == '/') sum = previous /= current;
            
            this.log.unshift(this.previous + " " + this.operator + " " + this.current + " = " + sum);
            return sum.toFixed(4);
        },

        addToLog(variantImage){
            this.image = variantImage; 
        },

        clear(){
            this.current = "";
            this.previous = "";
            this.show = ""; 
            this.operator = ""; 
        }
    },
    computed: {
        title() {
            return this.brand + " " + this.product;
        }
    }
})
function switchScreen(){
    var x = document.getElementById("result");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
    var y = document.getElementById("log");
    if (y.style.display === "none") {
        y.style.display = "block";
    } else {
        y.style.display = "none";
    }
}