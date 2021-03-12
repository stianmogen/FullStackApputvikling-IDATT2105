const app = Vue.createApp({
    data() {
        return {
            previous: null,
            current: null,
            operator: null,
            answer: 'answer here',
            log: [],
            product: 'Kalkulator',
            brand: 'Team 17',
            description: 'Mogen, Schiøll-Johansen, Steira',
        }
    },
    methods: {
        setCurrent(event){
            
        },
        operate(o){
            previous = current; 
            operator = o;
            current = null; 
        },
        calculate(){
            if (operator == '+') previous += current;
            if (operator == '-') previous -= current;
            if (operator == '*') previous *= current;
            if (operator == '/') previous /= current; 
        },
        addToLog(variantImage){
            this.image = variantImage; 
        }
    },
    computed: {
        title() {
            return this.brand + " " + this.product;
        }
    }
})