const initialTodoMockData = [
		{id: 999, title: "Learn Javascript", isComplete: false},
    	{id: 888, title: "Learn HTML5", isComplete: true},
    	{id: 777, title: "Learn CSS3", isComplete: false},
];

var todoApp = {
  data: function () {
  return {
    message: 'Welcome to Todo App',
    addTodoInput: '',
    lists: [],
    hasError: false,
    progressObject: {
    		width: '0%'
		},
    }
  },
  computed: {
    filterLists: function(){
      return _.orderBy(this.lists, ['isComplete', false])
    },
  },
  mounted: function ()  {
  	let vm = this;
  	vm.lists = initialTodoMockData;
  	vm.measureProgress();
  },
  methods: {
    addTask: function(e){
      //e.preventDefault();
      
      if(!this.addTodoInput){
        this.hasError = true;
        return;
      }
      
      this.hasError = false;
      this.lists.push({id:this.lists.length+1, title: this.addTodoInput, isComplete: false});
      
      this.addTodoInput = '';
    },
    removeTask: function(list){
      let vm = this;
      var index = _.findIndex(this.lists, list);
      this.lists.splice(index, 1);
       vm.measureProgress();
    },
    updateTask: function(e, list){
      e.preventDefault();
      list.title = e.target.innerText;
      e.target.blur();
    },
    completeTask: function(e, list){
      let vm = this;
      list.isComplete = !list.isComplete;
      vm.measureProgress();
    },
    measureProgress: function () {
    	let total = this.lists.length;
    	
    	if (total === 0) {
    		this.progressObject = {
				width: '0%'
			};
			return 0;    	
    	}
    	
    	let results = _.filter(this.lists, function(item){
  			return item.isComplete === true;
		});
		
		
		let progress = results.length / total * 100;
		
		this.progressObject = {
			width: Math.round(progress, 2) + '%'
		};
		
		return Math.round(progress, 2);
    },
    
  }
};

Vue.createApp(todoApp).mount('#todoApp');