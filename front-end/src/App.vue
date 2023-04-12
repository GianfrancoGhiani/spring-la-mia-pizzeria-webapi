<template>
  <div class="container-md">
    <header>
      <nav class="navbar row">
        <div class="col-1">
          <button class="btn btn-info rounded-pill" title="Create a New Pizza" @click="activateForm = !activateForm">
            <i class="fa-solid fa-pizza-slice"></i><sup>+</sup>
          </button>
        </div>
        <form class="form-inline col row w-100">

          <div class="col-4 offset-7 d-flex align-items-center">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"
              v-model="filteredName">
            <button class="btn btn-info rounded-circle my-2" @click.prevent="getAllPizzas()">
              <i class="fa-solid fa-magnifying-glass"></i>
            </button>
          </div>
        </form>
      </nav>
    </header>
    <main class="py-3">
      <form action="#" class="p-3 my-3 creation" :class="{ 'd-none': !activateForm }">
        <h2>Create a New Pizza</h2>
        <div class="row row-cols-2">
          <div class="col">
            <label for="name">
              <h6>Name</h6>
            </label>
            <input type="text" class="form-control" name="name" id="name" v-model="formModel.name">
          </div>
          <div class="col">
            <label for="price">
              <h6>Price</h6>
            </label>
            <input type="number" step="0.01" min="0" class="form-control" name="price" id="price"
              v-model="formModel.price">
          </div>
          <div class="col">
            <label for="description">
              <h6>Description</h6>
            </label>
            <textarea class="form-control" name="description" id="description" v-model="formModel.description"></textarea>
          </div>
          <div class="col">
            <label>
              <h6>Ingredients</h6>
            </label>
            <div class="form-check" v-for="(i, index) in ingredients" :key="index">
              <input class="form-check-input" type="checkbox" name="ingredients[]" v-model="formModel.ingredients"
                :value="i.id" :id="i.id">
              <label class="form-check-label" :for="i.id">
                {{ i.name }}
              </label>
            </div>
          </div>
        </div>
        <button class="btn btn-info" @click.prevent="createPizza()">Create</button>
      </form>
      <div class="row row-cols-3">
        <div class="col p-3" v-for="p in pizzas" :key="p.id">
          <div class="card">
            <div class="card-header">{{ p.name }}</div>
            <div class="card-body">
              {{ p.description }}
              <h6>Price:
                <span :class="{ 'text-decoration-line-through text-danger': p.discountedPrice != p.price }">{{ p.price
                }}</span>
                <span class="mx-2" v-if="p.discountedPrice != p.price">{{ p.discountedPrice }}</span>
              </h6>
              <div class="text-end"><button class="btn btn-danger" @click="deletePizza(p.id)"><i
                    class="fa-solid fa-trash-can"></i></button></div>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>
<script>
import axios from 'axios';
import { store } from './store';
export default {
  data() {
    return {
      store,
      pizzas: [],
      ingredients: [],
      filteredName: '',
      activateForm: false,
      formModel: {
        name: '',
        description: '',
        price: 0,
        ingredients: []
      }
    }
  },
  methods: {
    getAllPizzas() {
      console.log(this.filteredName);
      axios.get(store.apiBaseUrl, { params: { name: this.filteredName } }).then((response) => {
        console.log(response);
        this.pizzas = response.data
      })
      axios.get(`${store.apiBaseUrl}/ingredients`).then((ing_resp) => {
        console.log(ing_resp);
        this.ingredients = ing_resp.data
      })
    },
    createPizza() {
      console.log(this.formModel);
      const options = {
        method: 'POST',
        headers: { 'content-type': 'application/x-www-form-urlencoded' },
        data: this.formModel,
        url: store.apiBaseUrl,
      };
      axios(options);
      location.reload();
    },
    deletePizza(id) {
      axios.delete(`${store.apiBaseUrl}/${id}`).then((res) => {
        console.log(res)
      }).catch((err) => {
        console.log(err)
      }).finally(() => {
        location.reload();
      })
    }
  },
  mounted() {
    this.getAllPizzas();
  },

}
</script>
<style scoped>
header {
  height: 3rem;
}

main {
  height: calc(100vh - 3rem);
  width: 100%;


}

.creation {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 3rem 7rem;
  border-radius: 1rem;
}

li {
  list-style: none;
}
</style>
