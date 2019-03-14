<template>
    <form class="modal-content" action="">
        <span  @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Lista soci</h1>
      <hr> 
 <br>
 	<div>
    <b-table striped hover :items="soci" :fields="fields" />
  </div>
    </div>     
 <br>
     		
		
		<br/><br/>
		
				
					
						<button @click="closeAll()" class=" cancelbtn btn-block">Cancel</button>
					
          

				
</form>
</template>
<script scoped>

export default {

  data() {
    return {
      msg: "HowTo call REST-Services:",
      response: [],
      errors: [],
      value: 75,
         fields: [
          {
            key: 'last_name',
            label:'Nome',
            sortable: false
          },
          {
            key: 'first_name',
            label:'Cognome',
            sortable: true
          },
          {
            key: 'age',
            label: 'Eta',
            sortable: false,
            // Variant applies to the whole column, including the header and footer
            variant: 'danger'
          }
        ],
        soci: [
          { isActive: true, age: 40, first_name: 'Dickerson', last_name: 'Macdonald' },
          { isActive: false, age: 21, first_name: 'Larsen', last_name: 'Shaw' },
          { isActive: false, age: 89, first_name: 'Geneva', last_name: 'Wilson' },
          { isActive: true, age: 38, first_name: 'Jami', last_name: 'Carney' }
        ]
    };
  },
   
  methods: {
       closeAll(){
      for(var i=0;i<document.getElementsByClassName('modal').length;i++){
        document.getElementsByClassName('modal')[i].style.display='none';
      }
    },
    
    // Fetches posts when the component is created.
    callRestService() {
      AXIOS.get(`/hello`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data;
          console.log(response.data);
        })
        .catch(e => {
          this.errors.push(e);
        });
    },
    onclick:function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
  }
  
};
</script>
<style scoped>
button {
  position: absolute;
  padding: 14px 20px;
  margin: 8px 25% ;
  border: none;
  cursor: pointer;
  width: 50% ;
  opacity: 0.9;
}

button:hover {
  opacity: 1;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: #f44336;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn{
  position: relative;
  padding-left:30;
  width: 50%;
}
</style>

