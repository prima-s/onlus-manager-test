<template>
    <form class="modal-content" action="">
        <span  @click="closeAll()" class="close" title="Close Modal">&times;</span>
    <div class="container">
      <h1>Rimuovi voce</h1>
      <hr> 
      <h4>Lista voci da eliminare</h4>
      <div id="app" class="text-uppercase text-bold">id selected: {{selected}}</div>
 <br><div id="app">
	
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>
					<label class="form-checkbox">
    <input type="checkbox" v-model="selectAll" @click="select">
    <i class="form-icon"></i>
  </label>
				</th>
				<th>id</th>
				<th>name</th>
				
			</tr>
		</thead>
		<tbody>
			<tr v-for="(i, index) in items" :key="index">
				<td>
					<label class="form-checkbox">
    					<input type="checkbox" :value="i.id" v-model="selected">
						<i class="form-icon"></i>
  					</label>
				</td>
				<td>{{i.id}}</td>
				<td>{{i.name}}</td>
			
			</tr>
		</tbody>
	</table>
</div>    </div>     
 <br>
     		
		
		<br/><br/>
		
				
					
						<button @click="closeAll()" class="cancelbtn btn-block">Cancel</button>
					
          

				
</form>
</template>
<script scoped>

export default {

  data() {
    return {
      response: [],
      errors: [],
         
		items: [
			{
				id: "id1",
				name: "John Doe",
				email: "email@example.com"
			},
			{
				id: "id2",
				name: "Jone Doe",
				email: "email2@example.com"
			}
		],
		selected: [],
		selectAll: false,
		voci: null
    };
  },
  created() {
    AXIOS.get(`/voci`)
            .then(response => {
              // JSON responses are automatically parsed.
              this.voci = response.data;
              console.log(response.data);
            })
            .catch(e => {
              this.errors.push(e);
            });
  },
  methods: {
      select() {
			this.selected = [];
			if (!this.selectAll) {
				for (let i in this.items) {
					this.selected.push(this.items[i].id);
				}
			}
		},
       closeAll(){
      for(var i=0;i<document.getElementsByClassName('modal').length;i++){
        document.getElementsByClassName('modal')[i].style.display='none';
      }
    },
    
    // Fetches posts when the component is created.
    rimuoviVoce() {
     var params = new URLSearchParams();
          params.append("voci", this.selected);

      AXIOS.post(`/rimuoviVoce`, params)
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data;
          console.log(response.data);
        })
        .catch(e => {
          this.errors.push(e);
        });
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

