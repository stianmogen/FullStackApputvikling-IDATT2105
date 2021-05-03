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
        }
    },
    methods: {
        setCurrent(value){
            selectedNumber = value.toString();
            this.current += selectedNumber;
            this.show = this.current; 
        },
        async setOperator(operator){
            selectedOperator = operator;

            if (this.current && this.previous)
                this.current = await this.calculate();

            this.operator = selectedOperator;

            if (this.current === '') return; 

            this.previous = this.current;
            this.show = this.current;
            this.current = ""; 
        },
        async equals(){
            this.current = await this.calculate()
            this.previous = this.current;
            this.show = this.current;
            this.current = ""; 
        },
        async calculate(){
            if (this.current === "")
                return;
            if (this.operator === '=')
                return; 
    
            const response = await fetch("http://localhost:8080/api/compute/", {
                method: "post", 
                headers: { "Content-Type": "application/json"},
                body: JSON.stringify({
                    current: this.previous, 
                    operator: this.operator,
                    number: this.current
                })
            });
            
            if (response.ok) {
                const answer = await response.json();
                this.log.unshift(this.previous + " " + this.operator + " " + this.current + " = " + answer);   
                return Math.round(answer * 10000) / 10000;
            } else {
                const answer = await response.json();
                alert(answer.message);
                return;
            }
        },
        addToLog(variantImage){
            this.image = variantImage; 
        },
        clear() {
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