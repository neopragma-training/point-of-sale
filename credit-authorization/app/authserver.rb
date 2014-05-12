# authserver.rb
require 'sinatra'
require 'json'

File.open("#{Dir.home}/tmp/authserver.pid", 'w') {|f| f.write Process.pid }

get '/' do
  'Credit authorization services'
end

get '/auth/:corrtag/:account/:amount' do
  return_message = {}
  return_message[:corrTag] = params['corrtag']
  return_message[:authCode] = params['account'][0].to_i < 5 && params['amount'].to_i < 1200 ? '30' : '99'
  return_message[:loyaltyAccrual] = '0'
  
  return_message.to_json
end

get '/:echo' do
  params[:echo]
end


