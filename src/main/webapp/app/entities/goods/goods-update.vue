<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="jhProject4App.goods.home.createOrEditLabel" v-text="$t('jhProject4App.goods.home.createOrEditLabel')">Create or edit a Goods</h2>
                <div>
                    <div class="form-group" v-if="goods.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="goods.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhProject4App.goods.name')" for="goods-name">Name</label>
                        <input type="text" class="form-control" name="name" id="goods-name"
                            :class="{'valid': !$v.goods.name.$invalid, 'invalid': $v.goods.name.$invalid }" v-model="$v.goods.name.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhProject4App.goods.price')" for="goods-price">Price</label>
                        <input type="number" class="form-control" name="price" id="goods-price"
                            :class="{'valid': !$v.goods.price.$invalid, 'invalid': $v.goods.price.$invalid }" v-model.number="$v.goods.price.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('jhProject4App.goods.photo')" for="goods-photo">Photo</label>
                        <div>
                            <img v-bind:src="'data:' + goods.photoContentType + ';base64,' + goods.photo" style="max-height: 100px;" v-if="goods.photo" alt="goods image"/>
                            <div v-if="goods.photo" class="form-text text-danger clearfix">
                                <span class="pull-left">{{goods.photoContentType}}, {{byteSize(goods.photo)}}</span>
                                <button type="button" v-on:click="clearInputImage('photo', 'photoContentType', 'file_photo')" class="btn btn-secondary btn-xs pull-right">
                                    <font-awesome-icon icon="times"></font-awesome-icon>
                                </button>
                            </div>
                            <input type="file" ref="file_photo" id="file_photo" v-on:change="setFileData($event, goods, 'photo', true)" accept="image/*" v-text="$t('entity.action.addimage')"/>
                        </div>
                        <input type="hidden" class="form-control" name="photo" id="goods-photo"
                            :class="{'valid': !$v.goods.photo.$invalid, 'invalid': $v.goods.photo.$invalid }" v-model="$v.goods.photo.$model" />
                        <input type="hidden" class="form-control" name="photoContentType" id="goods-photoContentType"
                            v-model="goods.photoContentType" />
                    </div>
                    <div class="form-group">
                        <label v-text="$t('jhProject4App.goods.category')" for="goods-category">Category</label>
                        <select class="form-control" id="goods-category" multiple name="category" v-model="goods.categories">
                            <option v-bind:value="getSelected(goods.categories, categoryOption)" v-for="categoryOption in categories" :key="categoryOption.id">{{categoryOption.category}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.goods.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./goods-update.component.ts">
</script>
